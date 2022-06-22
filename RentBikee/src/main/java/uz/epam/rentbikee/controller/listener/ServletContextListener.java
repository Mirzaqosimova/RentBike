package uz.epam.rentbikee.controller.listener;

import uz.epam.rentbikee.pool.ConnectionPool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

    public ServletContextListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
ConnectionPool.getInstance().destroyPool();
    }




}
