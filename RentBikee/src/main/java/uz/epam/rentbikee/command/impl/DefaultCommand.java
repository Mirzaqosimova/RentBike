package uz.epam.rentbikee.command.impl;


import uz.epam.rentbikee.command.Command;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "index.jsp";
    }
}
