package uz.epam.rentbikee.dao.impl;

import uz.epam.rentbikee.dao.BaseDao;
import uz.epam.rentbikee.dao.RoleDao;
import uz.epam.rentbikee.entity.Role;
import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.exception.DaoException;
import uz.epam.rentbikee.mapper.impl.RoleMapperImpl;
import uz.epam.rentbikee.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDaoImpl extends BaseDao<Role> implements RoleDao {

    private static final RoleMapperImpl mapper = RoleMapperImpl.getInstance();
    private static final String GET_BY_ROLE_NAME = "select id , role_name from roles where role_name = ?";
    private static final String GET_BY_ROLE_ID = "select id,role_name from roles where id = ?";
    private static final String FIND_ALL = "select id,role_name from roles";


    private static RoleDaoImpl instance = new RoleDaoImpl();

    private RoleDaoImpl() {
    }

    public static RoleDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<Role> findByRoleName(String name) {

        Optional<Role> role = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ROLE_NAME);) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                role = mapper.generateFromResultSet(resultSet);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

    @Override
    public boolean insert(Role role) throws DaoException {
        return false;
    }

    @Override
    public List<Role> findAll() throws DaoException {

        List<Role> roleList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {

            Optional<Role> role;
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                role = mapper.generateFromResultSet(resultSet);
                role.ifPresent(roleList::add);

            }

        } catch (SQLException e) {
            throw new DaoException(e);

        }
        return roleList;
    }

    @Override
    public Optional<Role> getById(Long id) {


        Optional<Role> role = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ROLE_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                role = mapper.generateFromResultSet(resultSet);
            }
            if (role.isPresent()) {
                return role;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }


}
