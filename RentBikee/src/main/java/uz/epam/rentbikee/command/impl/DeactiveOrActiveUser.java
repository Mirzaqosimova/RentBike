package uz.epam.rentbikee.command.impl;

import uz.epam.rentbikee.command.Command;
import uz.epam.rentbikee.exception.CommandException;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.service.impl.UserServiceImpl;
import uz.epam.rentbikee.util.AttributeName;
import uz.epam.rentbikee.util.Message;
import uz.epam.rentbikee.util.Page;

import javax.servlet.http.HttpServletRequest;

public class DeactiveOrActiveUser implements Command {

    private static final UserServiceImpl userservise = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        boolean result;
        try {
            result = userservise.activeOrdDeactiveUser(request);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (!result) {
            request.setAttribute(AttributeName.ERROR, Message.SOMETHING_WENT_WRONG);
        }
        return Page.USER_CATEGORY;
    }
}
