package uz.epam.rentbikee.service;

import uz.epam.rentbikee.entity.Detail;
import uz.epam.rentbikee.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface DetailService {

    boolean addDetail(HttpServletRequest request) throws ServiceException;
    List<Detail> findAll() throws ServiceException;

    boolean editDetail(HttpServletRequest request) throws ServiceException;

    boolean deteleDetail(HttpServletRequest request) throws ServiceException;
}
