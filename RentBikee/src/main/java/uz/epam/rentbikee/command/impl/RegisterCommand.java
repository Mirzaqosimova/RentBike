package uz.epam.rentbikee.command.impl;


import uz.epam.rentbikee.command.Command;
import uz.epam.rentbikee.command.Pages;
import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.mapper.UserMapperImpl;
import uz.epam.rentbikee.service.UserService;
import uz.epam.rentbikee.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.Optional;

public class RegisterCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        UserMapperImpl mapper = new UserMapperImpl();
        Optional<User> user = mapper.generateUserFromRequest(request);
if(user.isEmpty()){
    request.setAttribute("register_msg","fill in the list as a given rule");
    return Pages.REGISTER;
}
        UserService userService = UserServiceImpl.getInstance();


        return null;
    }

}
