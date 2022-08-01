package uz.epam.rentbikee.command.impl.category;


import uz.epam.rentbikee.command.Command;
import uz.epam.rentbikee.entity.CategoryBike;
import uz.epam.rentbikee.exception.CommandException;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.service.impl.CategoryServiceImpl;
import uz.epam.rentbikee.util.AttributeName;
import uz.epam.rentbikee.util.Message;
import uz.epam.rentbikee.util.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteCategoryCommand implements Command {

    private static final CategoryServiceImpl categoryService = CategoryServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        try {
           boolean isDelete = categoryService.deleteCategory(request);
            List<CategoryBike> category = categoryService.findAllCategory();
            request.setAttribute(AttributeName.ALL_CATEGORY, category);
           if(isDelete){
           request.setAttribute(AttributeName.MESSAGE, Message.THIS_CATEGORY_HAS_BIKES);
           }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return Page.ALL_CATEGORY;
    }
}
