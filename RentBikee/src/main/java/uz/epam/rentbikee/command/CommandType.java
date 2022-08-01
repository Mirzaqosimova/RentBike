package uz.epam.rentbikee.command;


import uz.epam.rentbikee.command.impl.bike.AddBikeCommand;
import uz.epam.rentbikee.command.impl.*;
import uz.epam.rentbikee.command.impl.auth.*;
import uz.epam.rentbikee.command.impl.bike.FindBikeFields;
import uz.epam.rentbikee.command.impl.category.AddCategoryCommand;
import uz.epam.rentbikee.command.impl.category.DeleteCategoryCommand;
import uz.epam.rentbikee.command.impl.category.FindAllCategoryCommand;
import uz.epam.rentbikee.command.impl.category.UpdateCategoryCommand;
import uz.epam.rentbikee.command.impl.detail.AddDetailCommand;
import uz.epam.rentbikee.command.impl.detail.DeleteDetailCommand;
import uz.epam.rentbikee.command.impl.detail.FindAllDetailCommand;
import uz.epam.rentbikee.command.impl.detail.UpdateDetailCommand;
import uz.epam.rentbikee.exception.CommandException;
import uz.epam.rentbikee.validator.validImpl.ValidationImpl;

public enum CommandType {


    SIGN_UP(new SignUpCommand()),
    SIGN_IN(new SignInCommand()),
    LOGOUT(new LogOutCommand()),
    DEFAULT(new DefaultCommand()),
    FIND_ALL_USERS(new FindAllUsersCommand()),
    MANAGE_USER(new ManageUserCommand()),
    DELETE_USER(new DeleteUserCommand()),
    ADD_CATEGORY(new AddCategoryCommand()),
    FIND_ALL_CATEGORY(new FindAllCategoryCommand()),
    UPDATE_CATEGORY(new UpdateCategoryCommand()),
    DELETE_CATEGORY(new DeleteCategoryCommand()),
    ADD_DETAIL(new AddDetailCommand()),
    FIND_ALL_DETAIL(new FindAllDetailCommand()),
    UPDATE_DETAIL(new UpdateDetailCommand()),
    DELETE_DETAIL(new DeleteDetailCommand()),
    ADD_BIKE(new AddBikeCommand()),
    FIND_BIKE_FIELDS(new FindBikeFields());
    final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandstr) throws CommandException {
        ValidationImpl validation = new ValidationImpl();
        boolean check = validation.validateCommand(commandstr);
        if (check) {
            CommandType current = CommandType.valueOf(commandstr.toUpperCase());
            return current.command;
        }
        return DEFAULT.getCommand();
    }

    public Command getCommand() {
        return command;
    }
}
