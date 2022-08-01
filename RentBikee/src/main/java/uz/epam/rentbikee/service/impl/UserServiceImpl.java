package uz.epam.rentbikee.service.impl;


import uz.epam.rentbikee.dao.impl.RoleDaoImpl;
import uz.epam.rentbikee.dao.impl.UserDaoImpl;
import uz.epam.rentbikee.entity.Role;
import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.entity.type.RoleName;
import uz.epam.rentbikee.exception.DaoException;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.mapper.impl.UserMapperImpl;
import uz.epam.rentbikee.payload.UserDto;
import uz.epam.rentbikee.service.UserService;
import uz.epam.rentbikee.util.AttributeName;
import uz.epam.rentbikee.util.Message;
import uz.epam.rentbikee.util.ParametrName;
import uz.epam.rentbikee.validator.validImpl.ValidationImpl;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private static final UserDaoImpl userDao = UserDaoImpl.getInstance();
    private static final RoleDaoImpl roleDao = RoleDaoImpl.getInstance();
    private static final UserMapperImpl mapper = UserMapperImpl.getInstance();


    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {

    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public User authenticate(HttpServletRequest request) throws ServiceException {
        String username = request.getParameter(ParametrName.USERNAME);
        String password = request.getParameter(ParametrName.PASSWORD);
        ValidationImpl validation = new ValidationImpl();
        boolean phoneNumberValid = validation.isUserNameValid(username);
        boolean passwordValid = validation.isPasswordValid(password);
        if (!(passwordValid && phoneNumberValid)) {
            request.setAttribute(AttributeName.LOGIN_MSG, Message.INCORRECT);
            return null;
        }

        password = Base64.getEncoder().encodeToString(password.getBytes());
        UserDaoImpl userDao = UserDaoImpl.getInstance();

        Optional<User> user;
        try {
            user = userDao.authenticate(username, password);
            if (user.isPresent()) {
                return user.get();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public boolean registration(HttpServletRequest request) throws ServiceException {

        Optional<User> user = mapper.generateAndValidRequest(request);
        if (user.isEmpty()) {
            request.setAttribute(AttributeName.REGISTER_MSG, Message.FILL_CLEARLY);
            return false;
        }
        User newUser = user.get();

        try {
            boolean exist = userDao.existByPhoneNumber(newUser.getPhoneNumber());
            if (exist) {
                request.setAttribute(AttributeName.REGISTER_MSG, Message.PHONE_NUMBER_EXIST);
                return false;
            }
            Optional<Role> byRoleName = roleDao.findByRoleName(RoleName.USER.name());
            byRoleName.ifPresent(newUser::setRole);

            boolean insert = userDao.insert(newUser);
            if (insert) {
                request.setAttribute(AttributeName.USERNAME, newUser.getUsername());
                return true;
            }


        } catch (DaoException e) {
            throw new ServiceException(e);
        }


        return false;
    }

    @Override
    public List<UserDto> findAllUsers() throws ServiceException {
        List<UserDto> userDtoList;
        try {
            List<User> userList = userDao.findAll();
            userDtoList = userList.stream()
                    .filter(user -> !user.isDelete() && user.getRole().getRole().equals(RoleName.USER))
                    .map(mapper::generateUserDtofromUser)
                    .collect(Collectors.toList());
            return userDtoList;
        } catch (DaoException e) {
            throw new ServiceException("users didn't find", e);
        }

    }

    @Override
    public boolean activeOrdDeactiveUser(HttpServletRequest request) throws ServiceException {
        Long id = validAndCastUserId(request);
        if( id == null){
           request.setAttribute(AttributeName.ERROR, Message.SOMETHING_WENT_WRONG);
           return true;
       }

        boolean result = false;
        try {
            Optional<User> byId = userDao.findById(id);
            if (byId.isPresent()) {
                if (byId.get().isBlock()) {
                    result = userDao.updateUserActive(id, false);
                } else {
                    result = userDao.updateUserActive(id, true);
                }
            }
        } catch (DaoException e) {
           throw new ServiceException(e);
        }

        return result;
    }

    @Override
    public boolean deleteUser(HttpServletRequest request) throws ServiceException {
        Long id = validAndCastUserId(request);
        if( id == null){
            request.setAttribute(AttributeName.ERROR, Message.SOMETHING_WENT_WRONG);
            return false;
        }
        boolean result = false;
        try {
            Optional<User> byId = userDao.findById(id);
            if (byId.isPresent()) {
                String number = byId.get().getPhoneNumber() + "&"+ generateRandomNumbers();
              result = userDao.changePhoneNumberAndIsDeletetrue(id,number);
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    private String generateRandomNumbers(){
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);
        return String.format("%05d", num);
    }

    private Long validAndCastUserId(HttpServletRequest  request){
        String parameter = request.getParameter(ParametrName.USER_ID);
        ValidationImpl validation = new ValidationImpl();
        boolean validateId = validation.validateId(parameter);
        if (!validateId) {
            return null;
        }
        return Long.valueOf(parameter);
    }
}
