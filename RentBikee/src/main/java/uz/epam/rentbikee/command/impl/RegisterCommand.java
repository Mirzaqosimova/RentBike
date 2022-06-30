package uz.epam.rentbikee.command.impl;


import uz.epam.rentbikee.command.Command;
import uz.epam.rentbikee.exception.CommandException;
import uz.epam.rentbikee.util.AttributeName;
import uz.epam.rentbikee.util.Message;
import uz.epam.rentbikee.util.Page;
import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.mapper.impl.UserMapperImpl;
import uz.epam.rentbikee.service.UserService;
import uz.epam.rentbikee.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class RegisterCommand implements Command {

    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        try {
            boolean registration = userService.registration(request);
            if (registration) {
                return Page.MAIN;
            }

        } catch (ServiceException e) {
            throw new CommandException(e);

        }
        return Page.REGISTER;

    }

}
