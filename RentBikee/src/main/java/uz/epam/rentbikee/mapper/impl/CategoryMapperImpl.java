package uz.epam.rentbikee.mapper.impl;

import uz.epam.rentbikee.entity.CategoryBike;
import uz.epam.rentbikee.entity.Role;
import uz.epam.rentbikee.entity.type.RoleName;
import uz.epam.rentbikee.mapper.Mapper;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CategoryMapperImpl implements Mapper<CategoryBike> {
    private static final CategoryMapperImpl instance  = new CategoryMapperImpl();

    private CategoryMapperImpl(){

    }

    public static CategoryMapperImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<CategoryBike> generateFromResultSet(ResultSet resultSet) {
        Long id = null;
        String name = null;
        try {
            id = resultSet.getLong(1);
            name = resultSet.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CategoryBike categoryBike = new CategoryBike();
        if (name != null) {
            categoryBike.setName(name);
            categoryBike.setId(id);
        }

        return Optional.of(categoryBike);
    }

    @Override
    public Optional<CategoryBike> generateAndValidRequest(HttpServletRequest request) {
        return Optional.empty();
    }
}
