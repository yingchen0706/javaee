package appdesign2.filter;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import appdesign2.action.SaveProductAction;
import appdesign2.form.ProductForm;
import appdesign2.model.Product;

/**
 * Servlet Filter implementation class DispatcherFilter
 */
@WebFilter("/*")
public class DispatcherFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		// pass the request along the filter chain
		String uri = ((HttpServletRequest)request).getRequestURI();
		String action = uri.substring(uri.lastIndexOf('/') + 1);
		
		String dispatchURL = null;
		
		if ("input-product".equals(action)) {
			dispatchURL = "jsp/ProductForm.jsp";
		} else if ("save-product".equals(action)){
			ProductForm prodForm = new ProductForm();
			prodForm.setName(request.getParameter("name"));
			prodForm.setDescription(request.getParameter("description"));
			prodForm.setPrice(request.getParameter("price"));
			
			Product prod = new Product();
			prod.setName(prodForm.getName());
			prod.setDescription(prodForm.getDescription());
			prod.setPrice(new BigDecimal(prodForm.getPrice()));
			
			SaveProductAction sProdAc = new SaveProductAction();
			sProdAc.save(prod);
			
			dispatchURL = "jsp/ProductDetail.jsp";
			request.setAttribute("product", prod);
		}
		
		if (dispatchURL != null) {
			RequestDispatcher reqDispatcher = request.getRequestDispatcher(dispatchURL);
			reqDispatcher.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
