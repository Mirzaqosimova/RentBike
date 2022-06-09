package uz.epam.rentbikee.command.impl;


import uz.epam.rentbikee.command.Command;
import uz.epam.rentbikee.mapper.MapperImpl;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        MapperImpl mapper = new MapperImpl();
        mapper.generateUserFromRequest(request);

        return null;
    }

}
