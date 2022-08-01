package uz.epam.rentbikee.service.impl;

import uz.epam.rentbikee.dao.impl.RoleDaoImpl;
import uz.epam.rentbikee.entity.Role;
import uz.epam.rentbikee.exception.DaoException;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.service.RoleService;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private static final RoleDaoImpl roleDao = RoleDaoImpl.getInstance();

    private static final RoleServiceImpl instance = new RoleServiceImpl();

    private RoleServiceImpl() {

    }

    public static RoleServiceImpl getInstance() {
        return instance;
    }


    @Override
    public List<Role> findAll() throws ServiceException {
        List<Role> roleList;
        try {
            roleList = roleDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return roleList;
    }
}
