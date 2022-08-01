package uz.epam.rentbikee.mapper.impl;

import uz.epam.rentbikee.dao.impl.RoleDaoImpl;
import uz.epam.rentbikee.entity.Role;
import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.mapper.Mapper;
import uz.epam.rentbikee.payload.UserDto;
import uz.epam.rentbikee.util.ParametrName;
import uz.epam.rentbikee.validator.validImpl.ValidationImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;

public class UserMapperImpl implements Mapper {


    private static UserMapperImpl instance = new UserMapperImpl();

    private UserMapperImpl() {

    }

    public static UserMapperImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> generateFromResultSet(ResultSet resultSet) {
        Optional<User> optionalUser = Optional.empty();
        User user = new User();
        try {
            Long id = resultSet.getLong(1);
            double balance = resultSet.getDouble(2);
            String fullName = resultSet.getString(3);
            boolean isBlock = resultSet.getBoolean(4);
            String password = resultSet.getString(5);
            String phoneNum = resultSet.getString(6);
            String username = resultSet.getString(7);
            boolean isDelete = resultSet.getBoolean(8);


            user.setId(id);
            user.setBalance(balance);
            user.setBlock(isBlock);
            user.setFullname(fullName);
            user.setPassword(password);
            user.setUsername(username);
            user.setPhoneNumber(phoneNum);
            user.setDelete(isDelete);
            optionalUser = Optional.of(user);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalUser;
    }


    @Override
    public Optional<User> generateAndValidRequest(HttpServletRequest request) {

        String fullname = request.getParameter(ParametrName.FULLNAME);
        String username = request.getParameter(ParametrName.USERNAME);
        String password = request.getParameter(ParametrName.PASSWORD);
        String phoneNum = request.getParameter(ParametrName.PHONE_NUMBER);

        ValidationImpl validation = new ValidationImpl();
        boolean userFieldsValid = validation.isUserFieldsValid(phoneNum,
                fullname,
                username,
                password);
        Optional<User> user = Optional.empty();
        password = Base64.getEncoder().encodeToString(password.getBytes());
        if (userFieldsValid) {
            user = Optional.ofNullable(User.builder()
                    .username(username)
                    .balance(0)
                    .phoneNumber(phoneNum)
                    .fullname(fullname)
                    .isBlock(false)
                    .password(password)
                    .build());

        }

        return user;
    }

    public UserDto generateUserDtofromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFullname(user.getFullname());
        userDto.setBlock(user.isBlock());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setUsername(user.getUsername());
        userDto.setBalance(userDto.getBalance());
        return userDto;
    }

}
