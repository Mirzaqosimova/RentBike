package uz.epam.rentbikee.dao;

import uz.epam.rentbikee.entity.Bike;
import uz.epam.rentbikee.exception.DaoException;

import java.util.List;

public interface BikeDao {

    boolean existByCategoryId(Long id) throws DaoException;

    boolean existByDetailId(Long validId) throws DaoException;
}
