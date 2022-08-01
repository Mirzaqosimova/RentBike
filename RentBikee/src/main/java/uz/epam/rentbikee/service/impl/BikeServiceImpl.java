package uz.epam.rentbikee.service.impl;

import uz.epam.rentbikee.service.BikeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class BikeServiceImpl implements BikeService {
    private static  final String PATH = "D:\\RentBike1\\rentBike\\RentBikee\\src\\main\\resources\\images\\";

    private static final BikeServiceImpl instance = new BikeServiceImpl();

    private BikeServiceImpl() {

    }

    public static BikeServiceImpl getInstance() {
        return instance;
    }



    @Override
    public boolean addBike(HttpServletRequest request) {
        String brandName = request.getParameter("brandName");
        String description = request.getParameter("description");
        String size = request.getParameter("size");
        String status = request.getParameter("categoryId");
        String time = request.getParameter("time");
        String price = request.getParameter("price");
        String addressDesc = request.getParameter("addresDesc");
        String addresCity = request.getParameter("addresCity");
        String addresStreet = request.getParameter("addresStreet");
        String[] detailId = request.getParameterValues("detailIds");
        String fileName = saveFile(request);

        return false;
    }

    private String saveFile(HttpServletRequest request){
        String fileName = null;
        try {
            for (Part part : request.getParts()) {
                fileName = getFileName(part);
            }
            Part filePart = request.getPart("photo");
            System.out.println(filePart.toString());
            String[] split =fileName.split("\\.");
            fileName = UUID.randomUUID() + "." +split[split.length - 1];
            Path path = Paths.get(PATH+fileName);
            Files.copy(filePart.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }

    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
