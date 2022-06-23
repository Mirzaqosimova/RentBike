package uz.epam.rentbikee.dao;


import uz.epam.rentbikee.entity.Role;

import java.util.List;

public interface RoleDao{

     Role findByRoleName(String name);

}
