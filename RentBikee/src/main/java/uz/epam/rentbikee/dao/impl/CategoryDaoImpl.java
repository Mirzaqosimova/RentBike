package uz.epam.rentbikee.dao.impl;

import uz.epam.rentbikee.dao.BaseDao;
import uz.epam.rentbikee.entity.CategoryBike;
import uz.epam.rentbikee.exception.DaoException;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.mapper.impl.CategoryMapperImpl;
import uz.epam.rentbikee.pool.ConnectionPool;
import uz.epam.rentbikee.service.impl.CategoryServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class CategoryDaoImpl extends BaseDao<CategoryBike> {

    private static final String INSERT_CATEGORY = "insert into category_bike(name) values (?)";
    private static final String SELECT_ALL_CATEGORY = "select id,name from category_bike";
    private static final String UPDATE_CATEGORY = "UPDATE category_bike set name = ? where id = ?";
    private static final String DELETE_CATEGORY = "delete " +
            "from category_bike " +
            "where id = ?";

    private static final CategoryMapperImpl categoryMapper = CategoryMapperImpl.getInstance();

    private static final CategoryDaoImpl instance = new CategoryDaoImpl();

    private CategoryDaoImpl() {

    }

    public static CategoryDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(CategoryBike categoryBike) throws DaoException {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CATEGORY)) {
             statement.setString(1,categoryBike.getName());
             statement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("category error", e);

        }
    }

    @Override
    public List<CategoryBike> findAll() throws DaoException {
        List<CategoryBike> categoryBikes = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CATEGORY)) {
             ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Optional<CategoryBike> categoryBike1 = categoryMapper.generateFromResultSet(resultSet);
                categoryBike1.ifPresent(categoryBikes::add);
            }

        } catch (SQLException e) {
            throw new DaoException("category error", e);

        }
            return categoryBikes;
    }

    @Override
    public Optional<CategoryBike> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean update(CategoryBike categoryBike) throws DaoException {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY)) {
            statement.setString(1,categoryBike.getName());
            statement.setLong(2,categoryBike.getId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new DaoException("category error", e);

        }
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY)) {
            statement.setLong(1, id);
            isUpdate = statement.execute();


        } catch (SQLException e) {
            throw new DaoException("User authenticate error", e);
        }
        return isUpdate;    }
}
