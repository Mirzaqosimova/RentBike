package uz.epam.rentbikee.mapper;

import uz.epam.rentbikee.entity.Role;
import uz.epam.rentbikee.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.util.Optional;

public interface Mapper {

   Optional<User> generateUserFromResultSet(ResultSet resultSet);

   Role generateRoleFromResultSet(ResultSet resultSet);

  Optional<User> generateUserFromRequest(HttpServletRequest request);
}
