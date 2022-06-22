package uz.epam.rentbikee.mapper;

import uz.epam.rentbikee.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.util.Optional;

public interface UserMapper {

   // User generateUserFromResultSet(ResultSet resultSet);

   Optional<User> generateUserFromRequest(HttpServletRequest request);
}
