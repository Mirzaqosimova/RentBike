package uz.epam.rentbikee.dao.impl;

import uz.epam.rentbikee.dao.BaseDao;
import uz.epam.rentbikee.entity.Detail;
import uz.epam.rentbikee.exception.DaoException;
import uz.epam.rentbikee.mapper.impl.DetailMapperImpl;
import uz.epam.rentbikee.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetailDaoImpl extends BaseDao<Detail> {

    private static final String INSERT_DETAIL = "insert into bike_details(name) values (?);";
    private static final String SELECT_DETAIL = "select id, name from bike_details";

    private static final String UPDATE_DETAIL = "UPDATE bike_details set name = ? where id = ?";


    private static final String DELETE_DETAIL = "delete " +
            "            from bike_details  " +
            "           where id = ?";
    private static final DetailMapperImpl detailMapper = DetailMapperImpl.getInstance();
    private static final DetailDaoImpl instance = new DetailDaoImpl();


    private DetailDaoImpl() {

    }

    public static DetailDaoImpl getInstance() {
        return instance;
    }



    @Override
    public boolean insert(Detail bikeDetail) throws DaoException {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_DETAIL)) {
             statement.setString(1,bikeDetail.getName());
             statement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Detail error", e);

        }

    }

    @Override
    public List<Detail> findAll() throws DaoException {
      List<Detail> bikeDetails = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_DETAIL)) {
            ResultSet resultSet = statement.executeQuery();
           while (resultSet.next()){
               Optional<Detail> bikeDetail = detailMapper.generateFromResultSet(resultSet);
               bikeDetail.ifPresent(bikeDetails::add);
           }
        } catch (SQLException e) {
            throw new DaoException("category error", e);

        }
        return bikeDetails;
    }

    @Override
    public Optional<Detail> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean update(Detail bikeDetail) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_DETAIL)) {
            statement.setString(1,bikeDetail.getName());
            statement.setLong(2,bikeDetail.getId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("category error", e);

        }    }

    @Override
    public boolean deleteById(Long validId) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_DETAIL)) {
            statement.setLong(1, validId);
            isUpdate = statement.execute();


        } catch (SQLException e) {
            throw new DaoException("User authenticate error", e);
        }
        return isUpdate;
    }
}
