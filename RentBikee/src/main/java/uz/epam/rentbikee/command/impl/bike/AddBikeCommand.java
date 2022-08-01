package uz.epam.rentbikee.command.impl.bike;

import uz.epam.rentbikee.command.Command;
import uz.epam.rentbikee.entity.Attachment;
import uz.epam.rentbikee.exception.CommandException;
import uz.epam.rentbikee.service.impl.BikeServiceImpl;
import uz.epam.rentbikee.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10, // 1 MB
        maxFileSize = 1024 * 1024 * 100,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddBikeCommand extends HttpServlet implements Command {
    private static final BikeServiceImpl bikeService = BikeServiceImpl.getInstance();


    @Override
    public String execute(HttpServletRequest request) throws CommandException {

          boolean result = bikeService.addBike(request);





        return null;
    }

}
