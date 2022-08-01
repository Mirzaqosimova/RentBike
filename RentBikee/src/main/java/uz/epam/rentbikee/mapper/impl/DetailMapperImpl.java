package uz.epam.rentbikee.mapper.impl;

import uz.epam.rentbikee.entity.Detail;
import uz.epam.rentbikee.mapper.Mapper;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DetailMapperImpl implements Mapper<Detail> {
    
    private static final DetailMapperImpl instance = new DetailMapperImpl();
    private DetailMapperImpl(){
        
    }

    public static DetailMapperImpl getInstance() {
        return instance;
    }
    
    @Override
    public Optional<Detail> generateFromResultSet(ResultSet resultSet) {
        Long id = null;
        String name = null;
        try {
            id = resultSet.getLong(1);
            name = resultSet.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Detail bikeDetail = new Detail();
        if (name != null) {
            bikeDetail.setName(name);
            bikeDetail.setId(id);
        }

        return Optional.of(bikeDetail);
    }

    @Override
    public Optional<Detail> generateAndValidRequest(HttpServletRequest request) {
        return Optional.empty();
    }
}
