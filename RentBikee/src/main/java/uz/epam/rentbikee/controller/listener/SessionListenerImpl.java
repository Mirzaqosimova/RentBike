package uz.epam.rentbikee.controller.listener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListenerImpl implements  HttpSessionListener {

    static Logger logger = LogManager.getLogger();


    @Override
    public void sessionCreated(HttpSessionEvent se) {
logger.log(Level.INFO, "sessionCreated: "+ se.getSession().getId());    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.log(Level.INFO, "sessionDestroyed: "+ se.getSession().getId());    }

}
