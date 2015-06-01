<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.iiitb.sis.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="icon" href="../../favicon.ico">

<title>User Profile</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/bootstrapblueswatch.css">

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
#noti {
	width: 300px !important;
	height: 400px !important;
}
</style>
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
	<link rel="stylesheet" href="css/bootstrap-multiselect.css" type="text/css"/>
	<script type="text/javascript">
    $(document).ready(function() {
        $('#interest').multiselect({
        	buttonWidth: '450px',
        		dropRight: true
        });
    });
    </script>
</head>
<body>
<%
boolean fprofileFlag=false; 	//true indicates your friends profile else your profile

%>
<s:if test="%{friendid !=null && friendid !=''}">
<%fprofileFlag=true;System.out.println("is Friend is true");%>
</s:if>
<s:else>
<% System.out.println("is Friend is false");
fprofileFlag=false;
%>
	
</s:else>

<%@ include file="userHeader.jsp"%>

	

	<div class="container">
          <%@ include file="userLeftNav.jsp" %>
          <h2 align="center">No Friend Found with ID:<s:property value="friendid"/></h2>
              
        <script src="/plugins/bootstrap-select.min.js"></script>
        <script src="/codemirror/jquery.codemirror.js"></script>
        <script src="/beautifier.js"></script>

       
       
        
        </div>

	
	<script src="/plugins/bootstrap-pager.js"></script>

	<!-- /container -->
</body>
</html>