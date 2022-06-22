package uz.epam.rentbikee.command;


import uz.epam.rentbikee.command.impl.DefaultCommand;
import uz.epam.rentbikee.command.impl.LogOutCommand;
import uz.epam.rentbikee.command.impl.LoginCommand;
import uz.epam.rentbikee.command.impl.RegisterCommand;
import uz.epam.rentbikee.exception.CommandException;
import uz.epam.rentbikee.valid.ValidationImpl;

public enum CommandType {


    REGISTRATION(new RegisterCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogOutCommand()),
    DEFAULT(new DefaultCommand());

    Command command;

    CommandType(Command command){
        this.command = command;
    }

    public static Command define(String com) throws CommandException {
        ValidationImpl validation = new ValidationImpl();
        boolean check = validation.validateCommand(com);
        if(check) {
            CommandType current = CommandType.valueOf(com.toUpperCase());
            return current.command;
        }
        return DEFAULT.getCommand();
    }

    public Command getCommand() {
        return command;
    }
}
