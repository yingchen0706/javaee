package filterdemo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class ImageProtectorFilter
 */
@WebFilter(filterName="ImageProtectorDemo", urlPatterns= {"*.png", "*.jpg", "*.git"})
public class ImageProtectorFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest)request;
		
		String referer = httpReq.getHeader("referer");
		if (referer != null) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		} else {
			throw new ServletException("resource not available");
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
