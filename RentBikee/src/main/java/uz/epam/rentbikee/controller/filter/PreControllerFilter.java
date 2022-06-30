package uz.epam.rentbikee.controller.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uz.epam.rentbikee.pool.ConnectionPool;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "PreControllerFilter", urlPatterns = "/controller")
public class PreControllerFilter implements Filter {

    static Logger logger = LogManager.getLogger();



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpServletRequest.getSession();
         //TODO check users

        logger.log(Level.INFO,"PreControllerFilter");
        chain.doFilter(request, response);
    }


}
