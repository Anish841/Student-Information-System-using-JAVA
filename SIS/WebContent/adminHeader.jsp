<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<% 
		if(session==null || session.getAttribute("login")==null){
			response.sendRedirect("Home.jsp");
		}
     %>

	<nav class="navbar navbar-default" role="navigation">
	
	<div>
		<p class="navbar-text" style="color: white;">Welcome <s:property value="#session.user.name"/></p>
	</div>
	<div>
		<p class="navbar-text" style="color: white;">Last logged on: <s:property value="#session.user.lastLoggedOn"/></p>
	</div>
	<div>
		<p class="navbar-text"><a href="logout" class="navbar-link">Logout</a></p>
	</div>
		
	</nav>
</div>