package uz.epam.rentbikee.service;

import uz.epam.rentbikee.entity.CategoryBike;
import uz.epam.rentbikee.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CategoryService {

    boolean addCategory(HttpServletRequest request) throws ServiceException;

    List<CategoryBike> findAllCategory() throws ServiceException;

    boolean editCategory(HttpServletRequest request) throws ServiceException;

    boolean deleteCategory(HttpServletRequest request) throws ServiceException;
}
