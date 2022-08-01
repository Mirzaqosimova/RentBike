package uz.epam.rentbikee.dao.impl;

import uz.epam.rentbikee.dao.BaseDao;
import uz.epam.rentbikee.dao.BikeDao;
import uz.epam.rentbikee.entity.AbstractEntity;
import uz.epam.rentbikee.entity.Bike;
import uz.epam.rentbikee.exception.DaoException;
import uz.epam.rentbikee.pool.ConnectionPool;
import uz.epam.rentbikee.util.ParametrName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BikeDaoImpl extends BaseDao<Bike> implements BikeDao {

    private static BikeDaoImpl instance = new BikeDaoImpl();

    private BikeDaoImpl() {
    }

    public static BikeDaoImpl getInstance() {
        return instance;
    }


    private static final String EXIST_BY_CATEGORY_ID = "select (case  when \n" +
            "            ((select bike.id from  bike \n" +
            "           join category_bike ct on ct.id = bike.category_id \n" +
            "                      where ct.id = ?) is null) then true else false end) as res";

    private static final String EXIST_BY_DETAIL_ID = "select (case  when " +
            "            ((select bike.id from  bike " +
            "            join bike_details_bike bdb on bdb.bike_id = bike.id " +
            "            join bike_details bd on bd.id = bdb.bike_details_id " +
            "                where bd.id = ?) is null) then false else true end) as res";

    @Override
    public boolean insert(Bike bike) throws DaoException {
        return false;
    }

    @Override
    public List<Bike> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Bike> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean update(Bike bike) throws DaoException {
        return false;
    }

    @Override
    public boolean deleteById(Long validId) {
        return false;
    }

    @Override
    public boolean existByCategoryId(Long id) throws DaoException {
        boolean isBlock;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(EXIST_BY_CATEGORY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isBlock = resultSet.getBoolean(ParametrName.RESULT);
                return isBlock;
            }
        } catch (SQLException e) {
            throw new DaoException("Delete category", e);
        }

        return false;
    }

    @Override
    public boolean existByDetailId(Long validId) throws DaoException {
        boolean isBlock;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(EXIST_BY_DETAIL_ID)) {
            statement.setLong(1, validId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isBlock = resultSet.getBoolean(ParametrName.RESULT);
                return isBlock;
            }
        } catch (SQLException e) {
            throw new DaoException("Delete category", e);
        }

        return false;
    }
}
