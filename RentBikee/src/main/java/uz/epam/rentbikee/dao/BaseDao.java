package uz.epam.rentbikee.dao;


import uz.epam.rentbikee.entity.AbsEntity;

import java.util.List;

public abstract class BaseDao<T  extends AbsEntity> {

    public abstract T insert(T t);

    public abstract boolean delete(T t);

    public abstract List<T> findAll();

    public abstract T update(T t);
}
