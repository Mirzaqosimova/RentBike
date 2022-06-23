package uz.epam.rentbikee.controller;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.rentbikee.command.Command;
import uz.epam.rentbikee.command.CommandType;
import uz.epam.rentbikee.exception.CommandException;
import uz.epam.rentbikee.pool.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/controller")
public class Controller extends HttpServlet {





    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        service(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      service(req,resp);
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String  com = request.getParameter("command");
        Command command = null;
        try {
            command = CommandType.define(com);
        } catch (CommandException e) {
            e.printStackTrace();
        }
        String page;
        try {
            page = command.execute(request);
            request.getRequestDispatcher(page).forward(request,response);
        } catch (CommandException | ServletException e) {
            response.sendError(500);


        }
    }
}