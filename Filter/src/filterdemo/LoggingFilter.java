package filterdemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LoggingFilter
 */
@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "LogFileName", value = "log.txt"), 
				@WebInitParam(name = "Prefix", value = "URI:")
		})
public class LoggingFilter implements Filter {
	private PrintWriter logger;
	private String prefix;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Logger.doFilter");
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		logger.println(new Date() + " " +
				prefix +
				httpRequest.getRequestURI());
		logger.flush();
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String logFileName = fConfig.getInitParameter("LogFileName");
		try {
			String path = fConfig.getServletContext().getRealPath("/") + logFileName;
			System.out.println(path);
			logger = new PrintWriter(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
		prefix = fConfig.getInitParameter("Prefix");
	}

	public void destroy() {
		System.out.print("destroy filter");
		if (logger != null) {
			logger.close();
		}
	}

}
