package listner;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class AppListner
 *
 */
@WebListener
public class AppListner implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		
		Map<String, String> countries = new HashMap<>();
		countries.put("CA", "Canada");
		countries.put("CN", "China");
		sc.setAttribute("countries", countries);
	}
}
