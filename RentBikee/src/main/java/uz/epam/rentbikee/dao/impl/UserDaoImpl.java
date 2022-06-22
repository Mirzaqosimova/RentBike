package uz.epam.rentbikee.dao.impl;


import uz.epam.rentbikee.dao.BaseDao;
import uz.epam.rentbikee.dao.UserDao;
import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.exception.DaoException;
import uz.epam.rentbikee.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

   private static final String SELECT_NAME = "SELECT\n" +
           " u.id,u.balance,u.fullname,\n" +
           "       u.is_block, u.password,\n" +
           "       u.phone_number\n" +
           "     , u.username, r.role_name\n" +
           "FROM users u\n" +
           "join roles r on r.id = u.role_id\n" +
           "where password = ? and phone_number = ?";


   private static final String SAVE_USER = " insert into users(balance, fullname, is_block, password, phone_number, username, role_id)\n" +
           " VALUES(?1,?2,?3,?4,?5,?6,?7) ";

   private static final String GET_USER_ROLE_ID = "select id\n" +
           "from roles\n" +
           "where role_name = 'USER'";

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public User insert(User user) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement =  connection.prepareStatement(GET_USER_ROLE_ID);
            ResultSet resultSet = statement.executeQuery();
if(resultSet.next()){
    Long id = resultSet.getLong(1);
}
             statement =  connection.prepareStatement(SAVE_USER);
//            statement.setString(1,"0");
//            statement.setString(2,phoneNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User authenticate(String phoneNumber, String password) throws DaoException {

        User user = null;

        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement =  connection.prepareStatement(SELECT_NAME);
            //statement.setString(1,login);
             statement.setString(1,password);
             statement.setString(2,phoneNumber);
             ResultSet resultSet = statement.executeQuery();
//MapperImpl mapper = new MapperImpl();

            if(resultSet.next()){

               // user =  mapper.generateUserFromResultSet(resultSet);

            }
        }
      catch (SQLException e) {
throw new DaoException("User authenticate error",e);

      }



        return user;
    }
}
