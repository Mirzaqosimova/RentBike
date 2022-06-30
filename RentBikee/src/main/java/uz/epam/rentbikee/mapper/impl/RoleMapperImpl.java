package uz.epam.rentbikee.mapper.impl;

import uz.epam.rentbikee.dao.impl.UserDaoImpl;
import uz.epam.rentbikee.entity.Role;
import uz.epam.rentbikee.entity.type.RoleName;
import uz.epam.rentbikee.mapper.Mapper;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RoleMapperImpl implements Mapper<Role> {

    private static RoleMapperImpl instance = new RoleMapperImpl();

    private RoleMapperImpl() {
    }

    public static RoleMapperImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<Role> generateFromResultSet(ResultSet resultSet) {
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

        return Optional.of(role1);
    }


    @Override
    public Optional<Role> generateAndValidRequest(HttpServletRequest request) {
        return Optional.empty();
    }
}
