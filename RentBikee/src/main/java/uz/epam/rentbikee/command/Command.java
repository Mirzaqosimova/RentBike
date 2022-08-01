package uz.epam.rentbikee.command;

import uz.epam.rentbikee.exception.CommandException;
import uz.epam.rentbikee.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {

  String execute(HttpServletRequest request) throws CommandException;

}
