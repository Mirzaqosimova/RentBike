package uz.epam.rentbikee.command.impl.auth;

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

public class ManageUserCommand implements Command {

    private static final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        boolean result;
        try {
            result = userService.activeOrdDeactiveUser(request);

            if (result) {
                request.setAttribute(AttributeName.ERROR, Message.SOMETHING_WENT_WRONG);
            } else {
                List<UserDto> allUsers = userService.findAllUsers();
                request.setAttribute(AttributeName.ALL_USER, allUsers);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return Page.USER_CATEGORY;
    }
}
