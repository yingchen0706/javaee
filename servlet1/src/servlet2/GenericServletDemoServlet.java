package servlet2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class GenericServletDemoServlet
 */
@WebServlet(name = "GenericServletDemoServlet",
		urlPatterns = { "/generic" }, 
		initParams = { 
				@WebInitParam(name = "admin", value = "Ying Chen"), 
				@WebInitParam(name = "email", value = "ying.c.chen@oracle.com")
		})
public class GenericServletDemoServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletConfig servletConfig = getServletConfig();
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print("<!DOCTYPE html>"
			+ "<html><head></head><body>"
			+ "Admin: " +  servletConfig.getInitParameter("admin")
			+ "<br/>Email: " + servletConfig.getInitParameter("email")
			+ "Admin: " +  getInitParameter("admin")
			+ "<br/>Email: " + getInitParameter("email")
			+ "</body></html>");
	}

}
