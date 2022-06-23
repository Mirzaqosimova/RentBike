package uz.epam.rentbikee.dao;


import uz.epam.rentbikee.entity.AbsEntity;
import uz.epam.rentbikee.exception.DaoException;

import java.util.List;

public abstract class BaseDao<T  extends AbsEntity> {

    public abstract T insert(T t) throws DaoException;

    public abstract List<T> findAll();

}
