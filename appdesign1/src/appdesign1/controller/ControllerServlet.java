package appdesign1.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appdesign1.action.SaveProductAction;
import appdesign1.form.ProductForm;
import appdesign1.model.Product;
import appdesign1.validator.ProductValidator;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(name = "ControllerServlet", urlPatterns = { "/input-product", "/save-product" })
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf('/') + 1);

		String dispatchURL = null;

		if ("input-product".equals(action)) {
			dispatchURL = "jsp/ProductForm.jsp";
		} else if ("save-product".equals(action)) {
			ProductForm prodForm = new ProductForm();
			prodForm.setName(request.getParameter("name"));
			prodForm.setDescription(request.getParameter("description"));
			prodForm.setPrice(request.getParameter("price"));

			ProductValidator validator = new ProductValidator();
			List<String> errors = validator.validate(prodForm);
			if (errors.isEmpty()) {

				Product prod = new Product();
				prod.setName(prodForm.getName());
				prod.setDescription(prodForm.getDescription());
				prod.setPrice(new BigDecimal(prodForm.getPrice()));

				SaveProductAction sProdAc = new SaveProductAction();
				sProdAc.save(prod);

				dispatchURL = "jsp/ProductDetail.jsp";
				request.setAttribute("product", prod);
			} else {
				request.setAttribute("errors", errors);
				request.setAttribute("form", prodForm);
				dispatchURL = "jsp/ProductForm.jsp";
			}
		}

		if (dispatchURL != null) {
			RequestDispatcher reqDispatcher = request.getRequestDispatcher(dispatchURL);
			reqDispatcher.forward(request, response);
		}
	}

}
