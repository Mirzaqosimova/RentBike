package uz.epam.rentbikee.service.impl;

import uz.epam.rentbikee.dao.impl.BikeDaoImpl;
import uz.epam.rentbikee.dao.impl.CategoryDaoImpl;
import uz.epam.rentbikee.entity.CategoryBike;
import uz.epam.rentbikee.exception.DaoException;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.service.CategoryService;
import uz.epam.rentbikee.util.AttributeName;
import uz.epam.rentbikee.util.Message;
import uz.epam.rentbikee.util.ParametrName;
import uz.epam.rentbikee.validator.validImpl.ValidationImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private static final CategoryDaoImpl categoryDao = CategoryDaoImpl.getInstance();
    private static final BikeDaoImpl bikeDao = BikeDaoImpl.getInstance();

    private static final CategoryServiceImpl instance = new CategoryServiceImpl();

    private CategoryServiceImpl() {

    }

    public static CategoryServiceImpl getInstance() {
        return instance;
    }


    @Override
    public boolean addCategory(HttpServletRequest request) throws ServiceException {

        String categoryName = request.getParameter(ParametrName.CATEGORY_NAME);
        ValidationImpl validation = new ValidationImpl();
        boolean check = validation.isNameValid(categoryName);
        if (!check) {
            request.setAttribute(AttributeName.ERROR, Message.FILL_CLEARLY);
            return false;
        }
        CategoryBike categoryBike = CategoryBike.builder()
                .name(categoryName)
                .build();
        boolean insert;
        try {
             insert = categoryDao.insert(categoryBike);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }


        return insert;
    }

    @Override
    public List<CategoryBike> findAllCategory() throws ServiceException{
          List<CategoryBike> categoryBikes;
        try {
            categoryBikes = categoryDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return categoryBikes;
    }

    @Override
    public boolean editCategory(HttpServletRequest request) throws ServiceException {

        String categoryid = request.getParameter(ParametrName.CATEGORY_ID);
        String categoryName = request.getParameter(ParametrName.CATEGORY_NAME);
        CategoryBike categoryBike = new CategoryBike();
        categoryBike.setName(categoryName);
        categoryBike.setId(Long.valueOf(categoryid));
        boolean edited;
        try {
             edited = categoryDao.update(categoryBike);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return edited;
    }

    @Override
    public boolean deleteCategory(HttpServletRequest request) throws ServiceException {
        String categoryId = request.getParameter(ParametrName.CATEGORY_ID);
        try {
         boolean  check =  bikeDao.existByCategoryId(validId(categoryId));
         if(!check){
             return true;
         }
       return  categoryDao.deleteById(validId(categoryId));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }
    private Long validId(String  request){
        ValidationImpl validation = new ValidationImpl();
        boolean validateId = validation.validateId(request);
        if (!validateId) {
            return null;
        }
        return Long.valueOf(request);
    }
}
