package uz.epam.rentbikee.command.impl.bike;

import uz.epam.rentbikee.command.Command;
import uz.epam.rentbikee.entity.CategoryBike;
import uz.epam.rentbikee.entity.Detail;
import uz.epam.rentbikee.exception.CommandException;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.service.impl.BikeServiceImpl;
import uz.epam.rentbikee.service.impl.CategoryServiceImpl;
import uz.epam.rentbikee.service.impl.DetailServiceImpl;
import uz.epam.rentbikee.util.AttributeName;
import uz.epam.rentbikee.util.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindBikeFields implements Command {

    private static final BikeServiceImpl bikeService = BikeServiceImpl.getInstance();
    private static final CategoryServiceImpl categoryService = CategoryServiceImpl.getInstance();
    private static final DetailServiceImpl detailService = DetailServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {


        List<CategoryBike> allCategory = null;
        try {
            allCategory = categoryService.findAllCategory();
            request.setAttribute(AttributeName.ALL_CATEGORY, allCategory);
            List<Detail> all = detailService.findAll();
            request.setAttribute(AttributeName.ALL_DETAILS, all);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }


        return Page.ADD_BIKE;
    }
}
