package listner;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Application Lifecycle Listener implementation class PerfStatListner
 *
 */
@WebListener
public class PerfStatListner implements ServletRequestListener {
	public void requestDestroyed(ServletRequestEvent sre) {
		ServletRequest sr = sre.getServletRequest();
		Long begin = (Long) sr.getAttribute("perf");
		if (begin != null) {
			Long end = System.nanoTime();
			String url = ((HttpServletRequest) sr).getRequestURI();
			System.out.print("URL: " + url);
			System.out.println("  Time: " + (end - begin));
		}
	}

	public void requestInitialized(ServletRequestEvent sre) {
		ServletRequest sr = sre.getServletRequest();
		sr.setAttribute("perf", System.nanoTime());
	}

}
