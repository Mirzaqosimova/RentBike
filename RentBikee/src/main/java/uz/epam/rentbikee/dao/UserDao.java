package uz.epam.rentbikee.dao;


import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.exception.DaoException;

import java.util.Optional;

public interface UserDao {

    User authenticate(String login, String password) throws DaoException;



}
