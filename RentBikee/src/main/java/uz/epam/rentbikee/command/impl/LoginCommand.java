package uz.epam.rentbikee.command.impl;


import uz.epam.rentbikee.command.Command;
import uz.epam.rentbikee.command.Page;
import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.exception.CommandException;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.service.UserService;
import uz.epam.rentbikee.service.impl.UserServiceImpl;
import uz.epam.rentbikee.valid.ValidationImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String username = request.getParameter("username");
        String password = request.getParameter("pass");
        ValidationImpl validation = new ValidationImpl();
        boolean phoneNumberValid = validation.isPhoneNumberValid(username);
        boolean passwordValid = validation.isPasswordValid(password);
        if(! passwordValid && phoneNumberValid){
            request.setAttribute("login_msg","incorrect Login or pass");
return Page.INDEX;
        }
        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();

        try {
            User user = userService.authenticate(username, password);
            if(user != null){
                request.setAttribute("user",user.getUsername());
                session.setAttribute("user_name", user.getUsername());
             page = Page.MAIN;
            } else {
                request.setAttribute("login_msg","incorrect Login or pass");
            page = Page.INDEX;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        session.setAttribute("current_page",page);
        return page;
    }
}
