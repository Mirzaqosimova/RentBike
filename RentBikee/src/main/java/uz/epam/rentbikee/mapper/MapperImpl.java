package uz.epam.rentbikee.mapper;

import uz.epam.rentbikee.entity.Role;
import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.entity.type.RoleName;
import uz.epam.rentbikee.valid.ValidationImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MapperImpl implements Mapper {

    @Override
    public Optional<User> generateUserFromResultSet(ResultSet resultSet) {
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
            Role role = resultSet.getObject(8, Role.class);

            user.setId(id);
            user.setBalance(balance);
            user.setBlock(isBlock);
            user.setFullname(fullName);
            user.setPassword(password);
            user.setUsername(username);
            user.setPhoneNumber(phoneNum);
            user.setRole(role);
            optionalUser = Optional.of(user);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalUser;
    }

    @Override
    public Role generateRoleFromResultSet(ResultSet resultSet) {
        Long id = null;
        String name = null;
        try {
            id = resultSet.getLong(1);
            name = resultSet.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Role role1 = new Role();
        if (name != null) {
            role1.setRole(RoleName.define(name));
            role1.setId(id);
        }

        return role1;
    }

    @Override
    public Optional<User> generateUserFromRequest(HttpServletRequest request) {

        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = request.getParameter("pass");
        String phoneNum = request.getParameter("phoneNum");

        ValidationImpl validation = new ValidationImpl();
        boolean userFieldsValid = validation.isUserFieldsValid(phoneNum, fullname, username, password);
        Optional<User> user = Optional.empty();
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


}
