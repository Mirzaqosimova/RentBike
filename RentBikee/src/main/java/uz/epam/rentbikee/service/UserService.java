package uz.epam.rentbikee.service;


import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.exception.DaoException;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.payload.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    User authenticate(HttpServletRequest request) throws ServiceException;

    boolean registration(HttpServletRequest request) throws ServiceException;

    List<UserDto> findAllUsers() throws ServiceException;

    boolean activeOrdDeactiveUser(HttpServletRequest request) throws DaoException, ServiceException;

    boolean deleteUser(HttpServletRequest request) throws ServiceException;
}
