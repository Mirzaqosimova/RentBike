package uz.epam.rentbikee.service.impl;


import uz.epam.rentbikee.dao.impl.UserDaoImpl;
import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.exception.DaoException;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.service.UserService;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl(){

    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public User authenticate(String phoneNumber, String password) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        User user;
        try {
            user = userDao.authenticate(phoneNumber, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }
}
