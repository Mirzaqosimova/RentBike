package uz.epam.rentbikee.mapper;

import uz.epam.rentbikee.entity.Role;
import uz.epam.rentbikee.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.util.Optional;

public interface Mapper<T> {

   Optional<T> generateFromResultSet(ResultSet resultSet);
   Optional<T> generateAndValidRequest(HttpServletRequest request);
}
