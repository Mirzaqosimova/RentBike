package uz.epam.rentbikee.dao.impl;


import uz.epam.rentbikee.dao.BaseDao;
import uz.epam.rentbikee.dao.UserDao;
import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.exception.DaoException;
import uz.epam.rentbikee.mapper.MapperImpl;
import uz.epam.rentbikee.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

   private static final String SELECT_NAME = "SELECT  u.id,u.balance,u.fullname,  u.is_block, u.password, u.phone_number, u.username, r.role_name FROM users u join roles r on r.id = u.role_id\n where password = ? and phone_number = ?";

   private static final String SAVE_USER = " insert into users(balance, fullname, is_block, password, phone_number, username, role_id) VALUES(?1,?2,?3,?4,?5,?6,?7) ";


   private static final String CHECK_PHONENUMBER_ISVALID = "select (case  when ((select id from  users where phone_number = ?) is null) then false else true end)";

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }


    @Override
    public User insert(User user) throws DaoException {

        Connection connection = ConnectionPool.getInstance().getConnection();
        Optional<User> user1 = Optional.empty();

        try {
            PreparedStatement  statement =  connection.prepareStatement(SAVE_USER);
            statement.setDouble(1,0);
            statement.setString(2,user.getFullname());
            statement.setBoolean(3,false);
            statement.setString(4,user.getPassword());
            statement.setString(5,user.getPhoneNumber());
            statement.setString(6,user.getUsername());
            statement.setLong(7,user.getRole().getId());
            ResultSet resultSet = statement.executeQuery();
            MapperImpl mapper = new MapperImpl();
            if(resultSet.next()){

                user1 =  mapper.generateUserFromResultSet(resultSet);

            }
        } catch (SQLException e) {
            throw new DaoException("User save error",e);
        }

//TODO CHECK USER
        return user1.get();
    }



    @Override
    public List<User> findAll() {
        return null;
    }



    @Override
    public Optional<User> authenticate(String phoneNumber, String password) throws DaoException {

        Optional<User> user = Optional.empty();

        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement =  connection.prepareStatement(SELECT_NAME);
             statement.setString(1,password);
             statement.setString(2,phoneNumber);
             ResultSet resultSet = statement.executeQuery();
   MapperImpl mapper = new MapperImpl();

            if(resultSet.next()){

                user =  mapper.generateUserFromResultSet(resultSet);

            }
        }
      catch (SQLException e) {
throw new DaoException("User authenticate error",e);

      }



        return user;
    }

    @Override
    public boolean excistByPhoneNumber(String phoneNumber) {
        return false;
    }
}
