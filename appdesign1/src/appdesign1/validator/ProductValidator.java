package appdesign1.validator;

import java.util.ArrayList;
import java.util.List;

import appdesign1.form.ProductForm;

public class ProductValidator {
	public List<String> validate(ProductForm prodForm) {
		List<String> errors = new ArrayList<>();
		String name = prodForm.getName();
		if (name == null || name.trim().isEmpty()) {
			errors.add("Product must have a name");
		}
		String price = prodForm.getPrice();
		if (price == null || price.trim().isEmpty()) {
			errors.add("Product must have a price");
		} else {
			try {
				Float.valueOf(price);
			} catch (Exception e) {
				errors.add("Product must have be a number");
			}
		}
		return errors;
	}
}
