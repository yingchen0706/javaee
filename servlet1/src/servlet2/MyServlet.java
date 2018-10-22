package servlet2;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet(name="myname", urlPatterns = {"/myServlet"})
public class MyServlet implements Servlet {
	private transient ServletConfig servletConfig;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.servletConfig = servletConfig;
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return servletConfig;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String servletName = getServletConfig().getServletName();
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		writer.print("<!DOCTYPE html>"
				+ "<html>"
				+ "<body>hello from " + servletName
				+ " " + req.getProtocol()
				+ "</body>"
				+ "</html>");
		
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return "my servlet";
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
