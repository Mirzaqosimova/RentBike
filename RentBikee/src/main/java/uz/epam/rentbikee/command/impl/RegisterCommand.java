package uz.epam.rentbikee.command.impl;


import uz.epam.rentbikee.command.Command;
import uz.epam.rentbikee.command.Page;
import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.mapper.MapperImpl;
import uz.epam.rentbikee.service.UserService;
import uz.epam.rentbikee.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class RegisterCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        MapperImpl mapper = new MapperImpl();
        Optional<User> user = mapper.generateUserFromRequest(request);
if(user.isEmpty()){
    request.setAttribute("register_msg","fill in the list as a given rule");
    return Page.REGISTER;
}
        UserService userService = UserServiceImpl.getInstance();
        try {
            userService.registration(user.get());
        } catch (ServiceException e) {
            request.setAttribute("register_msg","something went wrong");
        }

        return Page.MAIN;
    }

}
