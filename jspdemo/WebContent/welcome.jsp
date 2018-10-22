<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.util.Enumeration" %>
<%@page errorPage="errorHandler.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome page</title>
</head>
<body>
Welcome!
<br/>
<%
	DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
	String s = dateFormat.format(new Date());
	out.println("Today is " + s);
	
	for (Enumeration<String> e = request.getHeaderNames(); e.hasMoreElements();) {
		String header = e.nextElement();
		out.println(header + ": " + request.getHeader(header) + "</br>");
	}
%>
<hr/>
<%
	Integer.parseInt("throw me");
	out.println("Buffer size: " + response.getBufferSize() + "<br/>");
	out.println("Session id: " + session.getId() + "<br/>");
	out.println("Servlet name: " + config.getServletName() + "<br/>");
	out.println("Servlet info: " + application.getServerInfo() + "<br/>");
%>
</body>
</html>
