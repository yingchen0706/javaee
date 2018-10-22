package listner;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionListner
 *
 */
@WebListener
public class SessionListner implements HttpSessionListener, ServletContextListener {

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
	@Override
    public void sessionCreated(HttpSessionEvent se)  { 
    	HttpSession session = se.getSession();
    	ServletContext sc = session.getServletContext();
    	AtomicInteger cnt = (AtomicInteger) sc.getAttribute("userCnt");
    	if (cnt != null) {
    		int userCnt = cnt.getAndIncrement();
    		System.out.println("Session increased to: " + userCnt);
    	}
    	Product prod = new Product();
    	session.setAttribute("product", prod);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
	@Override
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	HttpSession session = se.getSession();
    	ServletContext sc = session.getServletContext();
    	AtomicInteger cnt = (AtomicInteger) sc.getAttribute("userCnt");
    	if (cnt != null) {
    		int userCnt = cnt.decrementAndGet();
    		System.out.println("Session decreased to: " + userCnt);
    	}
    	session.removeAttribute("product");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
	@Override
    public void contextInitialized(ServletContextEvent sce)  { 
		System.out.println("Session Created");
    	ServletContext sc = sce.getServletContext();
    	sc.setAttribute("userCnt", new AtomicInteger());
    }
	
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
	@Override
    public void contextDestroyed(ServletContextEvent sce)  { 
    }

}
