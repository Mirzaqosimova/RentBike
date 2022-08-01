package uz.epam.rentbikee.dao.impl;


import uz.epam.rentbikee.dao.BaseDao;
import uz.epam.rentbikee.dao.UserDao;
import uz.epam.rentbikee.entity.Role;
import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.exception.DaoException;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.mapper.impl.UserMapperImpl;
import uz.epam.rentbikee.pool.ConnectionPool;
import uz.epam.rentbikee.service.impl.RoleServiceImpl;
import uz.epam.rentbikee.util.ParametrName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    private static final RoleServiceImpl roleService = RoleServiceImpl.getInstance();

    private static final String SELECT_BY_USERNAME_AND_PASSWORD = "SELECT  u.id,u.balance,u.fullname,  u.is_block, u.password, u.phone_number, u.username , u.is_delete , r.id FROM users u join roles r on r.id = u.role_id where username = ? and password = ?";
    private static final String SELECT_ALL = "SELECT  u.id,u.balance,u.fullname,  u.is_block, u.password, u.phone_number, u.username, u.is_delete,  r.id FROM users u join roles r on r.id = u.role_id ";
    private static final String SAVE_USER = " insert into users(balance, fullname, is_block, password, phone_number, username, role_id, is_delete) VALUES(?,?,?,?,?,?,?,?) ";
    private static final String EXIST_BY_USERNAME = "select (case  when ((select id from  users where username = ?) is null) then false else true end) as res";
    private static final String EXIST_BY_PHONE_NUMBER = "select (case  when ((select id from  users where phone_number = ?) is null) then false else true end) as res";
    private static final String UPDATE_USER_BY_ISBLOCK = "UPDATE users SET is_block = ?  WHERE id = ?";
    private static final String GET_BY_ID = "SELECT  u.id,u.balance,u.fullname,  u.is_block, u.password, u.phone_number, u.username , u.is_delete , r.id FROM users u join roles r on r.id = u.role_id where u.id = ?";
    private static final String DELETE_USER = "UPDATE users SET is_delete = ?, phone_number = ? WHERE id = ?";
    private static final UserMapperImpl mapper = UserMapperImpl.getInstance();

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }


    @Override
    public boolean insert(User user) throws DaoException {


        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_USER)) {

            statement.setDouble(1, user.getBalance());
            statement.setString(2, user.getFullname());
            statement.setBoolean(3, user.isBlock());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getUsername());
            statement.setLong(7, user.getRole().getId());
            statement.setBoolean(8, false);
            statement.execute();

        } catch (SQLException e) {
            throw new DaoException("User save error", e);
        }

        return true;
    }


    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            List<Role> allRoles = roleService.findAll();
            Optional<User> user;
            while (resultSet.next()) {

                user = mapper.generateFromResultSet(resultSet);
                Long id = resultSet.getLong(9);
                Role role = allRoles.stream()
                        .filter(customer -> id.equals(customer.getId()))
                        .findAny()
                        .orElse(null);
                if (user.isPresent()) {
                    user.get().setRole(role);
                    userList.add(user.get());
                }

            }


        } catch (SQLException | ServiceException e) {
            throw new DaoException("User authenticate error", e);

        }


        return userList;
    }

    @Override
    public Optional<User> findById(Long id) throws DaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery();) {
                List<Role> allRoles = roleService.findAll();

                if (resultSet.next()) {
                    user = mapper.generateFromResultSet(resultSet);
                    Long roleId = resultSet.getLong(9);
                    Role role = allRoles.stream()
                            .filter(role1 -> roleId.equals(role1.getId()))
                            .findAny()
                            .orElse(null);

                    user.ifPresent(value -> value.setRole(role));
                }
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException("User authenticate error", e);
        }

        return user;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean deleteById(Long validId) {
        return false;
    }

    @Override
    public boolean changePhoneNumberAndIsDeletetrue(Long id, String phoneNumber) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setBoolean(1, true);
            statement.setString(2, phoneNumber);
            statement.setLong(3, id);
            isUpdate = statement.execute();


        } catch (SQLException e) {
            throw new DaoException("User authenticate error", e);
        }
        return isUpdate;
    }


    @Override
    public Optional<User> authenticate(String username, String password) throws DaoException {
        Optional<User> user = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_USERNAME_AND_PASSWORD)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try( ResultSet resultSet = statement.executeQuery();) {

                List<Role> allRoles = roleService.findAll();

            if (resultSet.next()) {

                user = mapper.generateFromResultSet(resultSet);
                Long id = resultSet.getLong(9);
                Role role = allRoles.stream()
                        .filter(role1 -> id.equals(role1.getId()))
                        .findAny()
                        .orElse(null);

                user.ifPresent(value -> value.setRole(role));
               }
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException("User authenticate error", e);
        }
        return user;
    }


    @Override
    public boolean existByPhoneNumber(String phoneNumber) throws DaoException {
        boolean isBlock;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(EXIST_BY_PHONE_NUMBER)) {
            statement.setString(1, phoneNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isBlock = resultSet.getBoolean(ParametrName.RESULT);
                return isBlock;
            }
        } catch (SQLException e) {
            throw new DaoException("exist by phone number error", e);
        }

        return false;
    }


    @Override
    public boolean existByUsername(String username) throws DaoException {
        boolean isBlock;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(EXIST_BY_USERNAME)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isBlock = resultSet.getBoolean(ParametrName.RESULT);
                return isBlock;
            }
        } catch (SQLException e) {
            throw new DaoException("User authenticate error", e);
        }

        return false;
    }

    @Override
    public boolean updateUserActive(Long id, boolean isblock) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BY_ISBLOCK)) {
            statement.setBoolean(1, isblock);
            statement.setLong(2, id);
            isUpdate = statement.execute();

        } catch (SQLException e) {
            throw new DaoException("User authenticate error", e);
        }
        return isUpdate;
    }
}
