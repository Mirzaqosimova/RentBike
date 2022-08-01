package uz.epam.rentbikee.dao;


import uz.epam.rentbikee.entity.AbstractEntity;
import uz.epam.rentbikee.entity.User;
import uz.epam.rentbikee.exception.DaoException;

import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T  extends AbstractEntity> {

    public abstract boolean insert(T t) throws DaoException;

    public abstract List<T> findAll() throws DaoException;

    public abstract Optional<T> findById(Long id) throws DaoException;

    public abstract boolean update(T t) throws DaoException;

    public abstract boolean deleteById(Long validId) throws DaoException;
}
