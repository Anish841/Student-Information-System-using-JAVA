<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.ArrayList" %>
<%@page import="org.iiitb.sis.model.Terms" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Student</title><link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrapswatch.css">
<link href="css/simple-sidebar.css" rel="stylesheet">
<script
	src="js/jquery-1.10.2.min.js"></script>
<script
	src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap-multiselect.css"
	type="text/css" />
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>

<link rel="stylesheet" href="css/bootstrap-multiselect.css"
	type="text/css" />
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
<script src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#example1').datepicker({
			format : "dd/mm/yyyy"
		});

	});
	function loadTerms()
	{
		document.getElementById("add").action="LoadUserTerms";
		$('#add').submit();
	}
	function navigate(){
		document.getElementById("add").action="navigate";
		$('#add').submit();
	}
	function unHide(){
		$('#program').show();
		$('#term').show();
	}
	function Hide(){
		$('#program').hide();
		$('#term').hide();
	}
</script>
<style type="text/css">
.modalBlock {
	display: block;
	position: relative;
}
</style>
</head>
<%
		String userT=(String)request.getAttribute("userT");
		boolean userFlag=false;
		if(userT!=null && userT.equalsIgnoreCase("U")){
			userFlag=true;
	%>
	<body onload="unHide();">
	<% }%>
<body>
	<div id="wrapper">

		<%@ include file="adminLeftNav.jsp"%>
		<%@ include file="adminHeader.jsp"%>

		<div class="row">
			<div class="modal modalBlock">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Please fill up the form</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal"  onsubmit=""
								enctype="multipart/form-data" method="post" id="add">
								<fieldset>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput">User_ID</label>
										<div class="col-sm-10">
											<input type="text" placeholder="USER_id" name="id"
												class="form-control" required value="<s:property value="id"/>" >
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput">UserName</label>
										<div class="col-sm-10">
											<input type="text" placeholder="name" name="name"
												class="form-control" required value="<s:property value="name"/>">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="pwd">Password
										</label>
										<div class="col-sm-10">
											<input type="password" placeholder="Password" name="pwd"
												class="form-control" required value="<s:property value="pwd"/>">
										</div>
									</div>

									<!-- Text input-->
									<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput">E-mail</label>
										<div class="col-sm-10">
											<input type="email" placeholder="email" name="email"
												id="inputEmail" data-error="That email address is invalid"
												class="form-control" required value="<s:property value="email"/>">
										</div>
									</div>

									<!-- Text input-->
									<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput">Mobile_no.</label>
										<div class="col-sm-4">
											<input type="text" placeholder="Mobile_number" name="mob"
												data-error="Only numbers are allowed in Mobile number field!!"
												class="form-control" required value="<s:property value="mob"/>">
										</div>

										<label class="col-sm-2 control-label" for="textinput">Address</label>
										<div class="col-sm-4">
											<input type="text" placeholder="Address" name="add"
												class="form-control" required value="<s:property value="add"/>">
										</div>
									</div>



									<!-- Text input-->
									<div class="form-group">



										<label class="col-sm-2 control-label">Date_of_birth</label>
										<div class="col-sm-10">
											<div class="hero-unit">
												<input type="text" placeholder="click to show datepicker"
													id="example1" name="dob" required value="<s:property value="dob"/>">


											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-2 control-label">Gender</label>
										<div class="col-sm-10">
											<input type="radio" name="gender" value="male" required>Male
										</div>

										<div class="col-sm-10">
											<input type="radio" name="gender" value="female" required>Female
										</div>
									</div>
									
									 
									<div class="form-group">
										<label class="col-sm-2 control-label">User_Type</label>
										<div class="col-sm-10">
											<input type="radio" name="usertype" value="A" required onclick="Hide();"><label>Admin</label>
										</div>

										<div class="col-sm-10">
											<input type="radio" id="stu" name="usertype" value="U" required onclick="unHide();" if(userFlag){selected="true"}><label>Student</label>
										</div>
										<div class="col-sm-20">
											<input type="radio" name="usertype" value="F" style="margin-left:3.0cm;" required onclick="Hide();"><label>Faculty</label>
										</div>
									</div>
									
								<% ArrayList<Terms> termsList = (ArrayList<Terms>)(request.getAttribute("termsList"));
									ArrayList<String> programmeList = (ArrayList<String>)(request.getAttribute("programmeList"));
									
								%>
									<div class="form-group" style="display:none;" id="program">
										<label for="select" class="col-sm-2 control-label">Programme</label>
										<div class="col-lg-8">
											<select class="form-control" id="programmeName" name="programmeName" onchange="loadTerms();" required>
											<%
												for(int i=0;programmeList != null && i<programmeList.size();i++){
													String t = programmeList.get(i);
													if(t!=null && t.equalsIgnoreCase((String)request.getAttribute("programmeName"))){
												%>
														<option value="<%=t%>"selected="selected"><%=t%></option>
													<%}else{%>
														<option value="<%=t%>"><%=t%></option>
												<%}} %>
											</select>
										</div>
									</div>
									<div class="form-group" style="display:none;" id="term">
										<label for="select" class="col-sm-2 control-label">Term</label>
										<div class="col-lg-8">
											<select class="form-control" id="termID" name="termID" required>
											<%
												for(int i=0;termsList != null && i<termsList.size();i++)
												{
													Terms t = termsList.get(i);	
											%>
														<option value="<%=t.getTerm_id()%>"><%=t.getTerm_name()%></option>
											<%}%>
											</select>
										</div>
									</div>
									
						</div>
						
						<div class="modal-footer">
							<input type="button" class="btn btn-primary" id="formsubmit" value="Add User" onclick="navigate();"/>
						</div>
						</fieldset>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>