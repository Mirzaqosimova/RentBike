package uz.epam.rentbikee.command.impl;

import uz.epam.rentbikee.command.Command;
import uz.epam.rentbikee.exception.CommandException;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.payload.UserDto;
import uz.epam.rentbikee.service.impl.UserServiceImpl;
import uz.epam.rentbikee.util.AttributeName;
import uz.epam.rentbikee.util.Message;
import uz.epam.rentbikee.util.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindAllUsers implements Command {

    private static final UserServiceImpl userService = UserServiceImpl.getInstance();

    //TODO Pre Auth annotation
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        try {
            List<UserDto> allUsers = userService.findAllUsers();
            request.setAttribute(AttributeName.ALL_USER, allUsers);
            page = Page.USER_CATEGORY;
        } catch (ServiceException e) {
            throw new CommandException(e);

        }

        return page;
    }
}
