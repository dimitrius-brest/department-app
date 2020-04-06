package by.derevitsky.soap;

import com.sun.xml.ws.transport.http.servlet.WSServletContextListener;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextAttributeListener, ServletContextListener {

    private final WSServletContextListener listener;

    public Listener() {
        this.listener = new WSServletContextListener();
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        listener.attributeAdded(event);
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        listener.attributeRemoved(event);
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        listener.attributeReplaced(event);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        listener.contextInitialized(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        listener.contextDestroyed(sce);
    }
}
