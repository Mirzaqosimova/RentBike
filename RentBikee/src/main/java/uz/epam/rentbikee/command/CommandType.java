package uz.epam.rentbikee.command;


import uz.epam.rentbikee.command.impl.*;
import uz.epam.rentbikee.exception.CommandException;
import uz.epam.rentbikee.validator.validImpl.ValidationImpl;

public enum CommandType {


    REGISTRATION(new RegisterCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogOutCommand()),
    DEFAULT(new DefaultCommand()),
    FIND_ALL_USERS(new FindAllUsers()),
    DEACTIVE_OR_ACTIVE_USER(new DeactiveOrActiveUser()),
    DELETE_USER(new DeleteUser()),
    FIND_ALL_USER(new FindAllUsers());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commanstr) throws CommandException {
        ValidationImpl validation = new ValidationImpl();
        boolean check = validation.validateCommand(commanstr);
        if (check) {
            CommandType current = CommandType.valueOf(commanstr.toUpperCase());
            return current.command;
        }
        return DEFAULT.getCommand();
    }

    public Command getCommand() {
        return command;
    }
}
