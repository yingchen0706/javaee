package listner;

import java.math.BigDecimal;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Product implements HttpSessionBindingListener {
	public String id;
	public String name;
	public BigDecimal price;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		String attrName = event.getName();
		System.out.println(attrName);
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		String attrName = event.getName();
		System.out.println(attrName);
	}

}
