package uz.epam.rentbikee.command.impl.auth;


import uz.epam.rentbikee.command.Command;
import uz.epam.rentbikee.entity.type.RoleName;
import uz.epam.rentbikee.util.AttributeName;
import uz.epam.rentbikee.util.Message;
import uz.epam.rentbikee.util.Page;
import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.exception.CommandException;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.service.UserService;
import uz.epam.rentbikee.service.impl.UserServiceImpl;
import uz.epam.rentbikee.validator.validImpl.ValidationImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignInCommand implements Command {

    private static final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page;

        try {
            User user = userService.authenticate(request);
            if (user != null) {
                request.setAttribute(AttributeName.USER, user);
                if (user.getRole().getRole().equals(RoleName.ADMIN)) {
                    page = Page.ADMIN_PAGE;
                } else {
                    page = Page.ADMIN_PAGE;
                }
            } else {
                request.setAttribute(AttributeName.LOGIN_MSG, Message.INCORRECT);
                page = Page.LOGIN;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
