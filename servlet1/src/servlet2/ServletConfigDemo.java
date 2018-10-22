package servlet2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet(name="ServletConfigDemo",
	urlPatterns = {"/servletConfigDemo"},
	initParams = {
		@WebInitParam(name="admin", value="yc"),
		@WebInitParam(name="email", value="yc@email.com")
	})
public class ServletConfigDemo implements Servlet {
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
				+ "<br/>" + getServletConfig().getInitParameter("email")
				+ "<br/>" + getServletConfig().getInitParameter("admin")
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
