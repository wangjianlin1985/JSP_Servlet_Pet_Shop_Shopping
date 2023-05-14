// 
// 
// 

package listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.ServletContextEvent;
import service.TypeService;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.ServletContextListener;

@WebListener
public class ApplicationListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener
{
    TypeService tsService;
    
    public ApplicationListener() {
        this.tsService = new TypeService();
    }
    
    public void contextInitialized(final ServletContextEvent sce) {
        sce.getServletContext().setAttribute("typeList", (Object)this.tsService.GetAllType());
    }
    
    public void contextDestroyed(final ServletContextEvent sce) {
    }
    
    public void sessionCreated(final HttpSessionEvent se) {
    }
    
    public void sessionDestroyed(final HttpSessionEvent se) {
    }
    
    public void attributeAdded(final HttpSessionBindingEvent sbe) {
    }
    
    public void attributeRemoved(final HttpSessionBindingEvent sbe) {
    }
    
    public void attributeReplaced(final HttpSessionBindingEvent sbe) {
    }
}
