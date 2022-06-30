package uz.epam.rentbikee.dao;


import uz.epam.rentbikee.entity.Role;

import java.util.Optional;

public interface RoleDao{

     Optional<Role> findByRoleName(String name);

}
