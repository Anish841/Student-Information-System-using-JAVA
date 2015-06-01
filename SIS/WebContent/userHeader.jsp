<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
    <%@ page import="java.util.*" %>
    <%@ page import="org.iiitb.sis.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
		if(session==null || session.getAttribute("login")==null){
			response.sendRedirect("Home.jsp");
		}
		ArrayList<Notification> notiList=(ArrayList<Notification>)session.getAttribute("notiList");
		int numNoti=0;
		if(notiList!=null && notiList.size()>0){
			Notification n=notiList.get(notiList.size()-1);
			numNoti=n.getLen();
		}
 %>
<nav class="navbar navbar-default">
		<div class="container">
			
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<form class="navbar-form navbar-left" action="searchAction" role="search">
						<div class="form-group">
							<div id="scrollable-dropdown-menu">
							
							
							<input id="friendid" name="friendid" type="text" style="width:200px;" class="form-control" placeholder="Enter UserID" autocomplete="off" >
							</div>
						</div>
						<button type="submit" class="glyphicon glyphicon-search"/ aria-hidden="true">
					</form>
					
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="userProfile.jsp"><s:property value="#session.user.name"/></a></li>
					<li><a href="userHome.jsp">Home</a></li>
					<li><a href="GradesAllSemAllSub">Grades</a></li>
					<!-- <li><a href="getEnrolledSubjects">Subjects</a></li>
					<li><a href="getAllSyllabus">Syllabus</a></li> -->
					<li><a href="AddEnrolledSubjects">Enrollment</a></li>
					<li><a href="youMayKnow.jsp">You May Know</a></li>
					<li><a href="Friends">My Friends</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Notifications <span class="badge"><%=numNoti%></span></a>
						<ul class="dropdown-menu" role="menu" id="noti">
							<li class="dropdown-header">Notifications</li>
							<%if(notiList!=null && notiList.size()>0){ 
								boolean flag=true;
								for(int i=0;i<notiList.size();i++){
									Notification n=(Notification)notiList.get(i);
									if(!n.getNotiType().equalsIgnoreCase("FRND_REQ")){
							%>
										<li><a href="<%=n.getUrl()%>"><%=n.getNotiName() %></a></li>	
							<%}} }%>
							<%if(notiList!=null && notiList.size()>0){ 
								boolean flag=true;
								for(int i=0;i<notiList.size();i++){
									Notification n=(Notification)notiList.get(i);
									if(n.getNotiType().equalsIgnoreCase("FRND_REQ")){
										if(flag){
							%>
										<li class="divider"></li>
											<li class="dropdown-header">Friend Request</li>
										<%flag=false;} %>
										<li><a href="<%=n.getUrl()%>"><%=n.getNotiName() %></a></li>	
							<%}} }%>
							
						</ul>
					</li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Settings
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="editProfileAction">Update Profile</a></li>							
							<li class="divider"></li>
							<li class="dropdown-header">Wanna Log out?</li>
							<li><a href="logout">Logout</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>