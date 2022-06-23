package uz.epam.rentbikee.service.impl;


import uz.epam.rentbikee.dao.RoleDao;
import uz.epam.rentbikee.dao.impl.RoleDaoImpl;
import uz.epam.rentbikee.dao.impl.UserDaoImpl;
import uz.epam.rentbikee.entity.Role;
import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.entity.type.RoleName;
import uz.epam.rentbikee.exception.DaoException;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {

    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public User authenticate(String phoneNumber, String password) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();

        Optional<User> user;
        try {
            user = userDao.authenticate(phoneNumber, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        //TODO CHECK USER IS PRESENT
        return user.get();
    }

    @Override
    public Optional<User> registration(User newUser) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        RoleDaoImpl roleDao = RoleDaoImpl.getInstance();
        Role byRoleName = roleDao.findByRoleName(RoleName.USER.name());
        Optional<User> user;
        newUser.setRole(byRoleName);
        try {
            user = Optional.ofNullable(userDao.insert(newUser));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }


}
