package uz.epam.rentbikee.service.impl;

import uz.epam.rentbikee.dao.impl.BikeDaoImpl;
import uz.epam.rentbikee.dao.impl.DetailDaoImpl;
import uz.epam.rentbikee.entity.Detail;
import uz.epam.rentbikee.exception.DaoException;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.service.DetailService;
import uz.epam.rentbikee.util.AttributeName;
import uz.epam.rentbikee.util.Message;
import uz.epam.rentbikee.util.ParametrName;
import uz.epam.rentbikee.validator.validImpl.ValidationImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DetailServiceImpl implements DetailService {

    private static final DetailDaoImpl detailDao = DetailDaoImpl.getInstance();
    private static final BikeDaoImpl bikeDao = BikeDaoImpl.getInstance();

    private static final DetailServiceImpl instance = new DetailServiceImpl();

    @Override
    public boolean addDetail(HttpServletRequest request) throws ServiceException {

        String detailName = request.getParameter(ParametrName.DETAIL_NAME);
        ValidationImpl validation = new ValidationImpl();
        boolean check = validation.isNameValid(detailName);
        if (!check) {
            request.setAttribute(AttributeName.ERROR, Message.FILL_CLEARLY);
            return false;
        }
        Detail detail = Detail.builder()
                .name(detailName)
                .build();
        boolean insert;
        try {
            insert = detailDao.insert(detail);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }



        return insert;
    }

    public static DetailServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Detail> findAll() throws ServiceException {
        List<Detail> all;
        try {
           all = detailDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return all;
    }

    @Override
    public boolean editDetail(HttpServletRequest request) throws ServiceException {
        String detailId = request.getParameter(ParametrName.DETAIL_ID);
        String detailName = request.getParameter(ParametrName.DETAIL_NAME);
   //TODO validatsiya
        Detail detail = new Detail();
   detail.setName(detailName);
   detail.setId(validId(detailId));
boolean edited;
        try {
            edited = detailDao.update(detail);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return edited;
    }

    @Override
    public boolean deteleDetail(HttpServletRequest request) throws ServiceException {
        String detailId = request.getParameter(ParametrName.DETAIL_ID);
        try {
            boolean  check =  bikeDao.existByDetailId(validId(detailId));
            if(check) {
            return true;
            }
            return  detailDao.deleteById(validId(detailId));

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
