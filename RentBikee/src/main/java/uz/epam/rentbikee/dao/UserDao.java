package uz.epam.rentbikee.dao;


import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.exception.DaoException;

public interface UserDao {

    User authenticate(String login, String password) throws DaoException;

 //   User register(User user);

}
