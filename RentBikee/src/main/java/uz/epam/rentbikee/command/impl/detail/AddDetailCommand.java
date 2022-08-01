package uz.epam.rentbikee.command.impl.detail;

import uz.epam.rentbikee.command.Command;
import uz.epam.rentbikee.entity.Detail;
import uz.epam.rentbikee.exception.CommandException;
import uz.epam.rentbikee.exception.ServiceException;
import uz.epam.rentbikee.service.impl.DetailServiceImpl;
import uz.epam.rentbikee.util.AttributeName;
import uz.epam.rentbikee.util.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddDetailCommand implements Command {

    private static final DetailServiceImpl detailService = DetailServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
    boolean result;
        try {
             result =  detailService.addDetail(request);
             if(result){
                 List<Detail> details = detailService.findAll();
                 request.setAttribute(AttributeName.ALL_DETAILS,details);
                 return Page.ALL_DETAIL;
             }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return Page.ALL_DETAIL;
    }
}
