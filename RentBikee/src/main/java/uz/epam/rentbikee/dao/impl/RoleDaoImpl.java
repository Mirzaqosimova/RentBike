package uz.epam.rentbikee.dao.impl;

import uz.epam.rentbikee.dao.RoleDao;
import uz.epam.rentbikee.entity.Role;
import uz.epam.rentbikee.mapper.MapperImpl;
import uz.epam.rentbikee.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RoleDaoImpl implements RoleDao {

    private static final String GET_USER_ROLE_ID = "select * from roles where role_name = ?";


    private static RoleDaoImpl instance = new RoleDaoImpl();

    private RoleDaoImpl() {
    }

    public static RoleDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Role findByRoleName(String name) {
        Connection connection = ConnectionPool.getInstance().getConnection();
Role role = null;
        try {
            PreparedStatement statement =  connection.prepareStatement(GET_USER_ROLE_ID);
            statement.setString(1,name);
            ResultSet  resultSet = statement.executeQuery();
            MapperImpl mapper = new MapperImpl();

            if(resultSet.next()){
                role = mapper.generateRoleFromResultSet(resultSet);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

          return role;
    }
}
