package uz.epam.rentbikee.mapper;

import uz.epam.rentbikee.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

public interface Mapper  {

    User generateUserFromResultSet(ResultSet resultSet);

    User generateUserFromRequest(HttpServletRequest request);
}
