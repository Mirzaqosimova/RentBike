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

@WebServlet(name = "helloServlet", value = {"/controller","*.do"})
public class Controller extends HttpServlet {

    static Logger logger = LogManager.getLogger();

    public void init() {
    ConnectionPool.getInstance();
        logger.log(Level.INFO, "++++<<<<---------->  Servlet init: "+ this.getServletInfo());

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String  com = request.getParameter("command");
        Command command = CommandType.define(com);
        String page;
        try {
            page = command.execute(request);
             request.getRequestDispatcher(page).forward(request,response);
        // response.sendRedirect(request.getContextPath()+"/"+page);
        } catch (CommandException e) {
        //  response.sendError(500);
        //throw new ServletException(e);
        request.setAttribute("error_msg",e.getCause());
        request.getRequestDispatcher("page/error/error_500.jsp");

        }

    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}