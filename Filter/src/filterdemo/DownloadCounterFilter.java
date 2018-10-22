package filterdemo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class DownloadCounterFilter
 */
@WebFilter(urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "name", value = "DownloadCounterFilter") })
public class DownloadCounterFilter implements Filter {
	ExecutorService service;
	Properties properties;
	File logFile;

	public DownloadCounterFilter() {
		service = Executors.newFixedThreadPool(1);
	}

	public void destroy() {
		service.shutdown();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// place your code here
		service.execute(new Runnable() {

			@Override
			public void run() {
				HttpServletRequest httpReq = (HttpServletRequest) request;
				String uri = httpReq.getRequestURI();
				String strCnt = properties.getProperty(uri);
				if (strCnt == null) {
					properties.setProperty(uri, "1");
				} else {
					int iCnt = Integer.valueOf(strCnt);
					iCnt++;
					properties.setProperty(uri, String.valueOf(iCnt));
				}
				try {
					properties.store(new FileWriter(logFile), "");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		ServletContext sc = fConfig.getServletContext();
		String realPath = sc.getRealPath("/");
		logFile = new File(realPath, "logFile.prop");

		try {
			if (!logFile.exists()) {
				logFile.createNewFile();
			}
			properties = new Properties();
			properties.load(new FileReader(logFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
