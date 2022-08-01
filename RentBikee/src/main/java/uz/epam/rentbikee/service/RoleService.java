package uz.epam.rentbikee.service;

import uz.epam.rentbikee.entity.Role;
import uz.epam.rentbikee.exception.ServiceException;

import java.util.List;

public interface RoleService {
    List<Role> findAll() throws ServiceException;
}
