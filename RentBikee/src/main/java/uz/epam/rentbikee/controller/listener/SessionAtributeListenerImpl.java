package uz.epam.rentbikee.controller.listener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAtributeListenerImpl implements HttpSessionAttributeListener {

    static Logger logger = LogManager.getLogger();

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        logger.log(Level.INFO, "attributeAdded: "+ sbe.getSession().getAttribute("user_name"));
        logger.log(Level.INFO, "attributeAdded: "+ sbe.getSession().getAttribute("current_page"));
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        logger.log(Level.INFO, "attributeReplaced: "+ sbe.getSession().getAttribute("current_page"));
        logger.log(Level.INFO, "attributeReplaced: "+ sbe.getSession().getAttribute("current_page"));
    }
}
