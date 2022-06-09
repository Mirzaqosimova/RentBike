package uz.epam.rentbikee.service;


import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.exception.ServiceException;

public interface UserService {

    User authenticate(String login, String password) throws ServiceException;
}
