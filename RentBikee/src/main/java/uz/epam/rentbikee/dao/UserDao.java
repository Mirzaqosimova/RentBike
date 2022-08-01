package uz.epam.rentbikee.dao;


import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.exception.DaoException;

import java.util.Optional;

public interface UserDao {

    Optional<User> authenticate(String login, String password) throws DaoException;

    boolean existByPhoneNumber(String phoneNumber) throws DaoException;

    boolean existByUsername(String username) throws DaoException;

    boolean updateUserActive(Long id, boolean isActive) throws DaoException;

    boolean changePhoneNumberAndIsDeletetrue(Long id, String phoneNumber) throws DaoException;




}
