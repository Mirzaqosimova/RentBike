package uz.epam.rentbikee.command;


import uz.epam.rentbikee.command.impl.DefaultCommand;
import uz.epam.rentbikee.command.impl.LogOutCommand;
import uz.epam.rentbikee.command.impl.LoginCommand;
import uz.epam.rentbikee.command.impl.RegisterCommand;

public enum CommandType {
    REGISTRATION(new RegisterCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogOutCommand()),
    DEFAULT(new DefaultCommand());

    Command command;

    CommandType(Command command){
        this.command = command;
    }

    public static Command define(String com){
        CommandType current = CommandType.valueOf(com.toUpperCase());
        return current.command;
    }

    public Command getCommand() {
        return command;
    }
}
