package uz.epam.rentbikee.mapper;

import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.entity.type.Role;
import uz.epam.rentbikee.valid.Validation;
import uz.epam.rentbikee.valid.ValidationImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserMapperImpl implements UserMapper {

//    @Override
//    public  User generateUserFromResultSet(ResultSet resultSet){
//        User  user = new User();
//        try {
//            Long id = resultSet.getLong(1);
//            double balance = resultSet.getDouble(2);
//            String fullName = resultSet.getString(3);
//            boolean isBlock = resultSet.getBoolean(4);
//            String password = resultSet.getString(5);
//            String phoneNum = resultSet.getString(6);
//            String username = resultSet.getString(7);
//            String roleName = resultSet.getString(8);
//
//            user.setId(id);
//            user.setBalance(balance);
//            user.setBlock(isBlock);
//            user.setFullname(fullName);
//            user.setPassword(password);
//            user.setUsername(username);
//            user.setPhoneNumber(phoneNum);
//            Role define = Role.define(roleName);
//            user.setRole(define);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//return user;
//    }

    @Override
    public Optional<User> generateUserFromRequest(HttpServletRequest request) {

        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = request.getParameter("pass");
        String phoneNum = request.getParameter("phoneNum");

        ValidationImpl validation = new ValidationImpl();
        boolean userFieldsValid = validation.isUserFieldsValid(phoneNum, fullname, username, password);
Optional<User> user = Optional.empty();
        if(userFieldsValid) {
             user = Optional.ofNullable(User.builder()
                     .username(username)
                     .balance(0)
                     .phoneNumber(phoneNum)
                     .fullname(fullname)
                     .isBlock(false)
                     .role(Role.USER)
                     .password(password)
                     .build());
            return user;
        }

        return user;
    }


}
