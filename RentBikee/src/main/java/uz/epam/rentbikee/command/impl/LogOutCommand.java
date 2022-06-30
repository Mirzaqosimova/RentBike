package uz.epam.rentbikee.command.impl;


import uz.epam.rentbikee.command.Command;
import uz.epam.rentbikee.util.Page;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return Page.INDEX;
    }
}
