<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.ArrayList" %>
<%@page import="org.iiitb.sis.model.Terms" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Course</title><link rel="stylesheet" href="css/bootstrap.min.css">
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
		$('#add').submit();
	}
</script>
<style type="text/css">
.modalBlock {
	display: block;
	position: relative;
}
</style>
</head>
<body>
	<div id="wrapper">

		<%@ include file="adminLeftNav.jsp"%>
		<%@ include file="adminHeader.jsp"%>

		<div class="row">
		
		<div class="col-md-9 personal-info">
      	<div id="spacer"></div>
        <s:if test="hasActionErrors()">
			<div class="alert alert-danger alert-dismissable">
				 <a class="panel-close close" data-dismiss="alert">×</a> 
				  <i class="fa fa-coffee"></i>
				  <s:actionerror/>
			</div>
		</s:if>
		<s:if test="hasActionMessages()">
			 <div class="alert alert-info alert-dismissable">
				 <a class="panel-close close" data-dismiss="alert">×</a> 
				  <i class="fa fa-coffee"></i>
				  <s:actionmessage/>
			</div>
		</s:if>
		
			<div class="modal modalBlock">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Add Course</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" action="LoadTerms" id="add" method="post">
								<fieldset>
								<%
									String subjectID = (String)request.getAttribute("subjectID");
									String subjectName = (String)request.getAttribute("subjectName");
									String credits = (String)request.getAttribute("credits");
									String programmeName = (String)request.getAttribute("programmeName");
									String termID = (String)request.getAttribute("termID");
									ArrayList<Terms> termsList = (ArrayList<Terms>)(request.getAttribute("termsList"));
									ArrayList<String> programmeList = (ArrayList<String>)(request.getAttribute("programmeList"));
								%>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput">Subject ID</label>
										<div class="col-sm-10">
											<input type="text" placeholder="Subject ID" name="subjectID" <%if(subjectID != null) {%>value="<%=subjectID%>" <%} %>
												class="form-control" required>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput">Subject Name</label>
										<div class="col-sm-10">
											<input type="text" placeholder="Subject Name" name="subjectName" <%if(subjectName != null) {%>value="<%=subjectName%>" <%} %>
												class="form-control" required>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput">Credits</label>
										<div class="col-sm-6">
											<input type="text" placeholder="Credits" name="credits" <%if(credits != null) {%>value="<%=credits%>" <%} %>
												class="form-control" required>
										</div>
									</div>
									<div class="form-group">
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
								</form>
								<form class="form-horizontal" action="AddCourseTerm" id="add" method="post">
								<input type="hidden" name="subjectID" value="<%=subjectID%>">
								<input type="hidden" name="subjectName" value="<%=subjectName%>">
								<input type="hidden" name="credits" value="<%=credits%>">
								<input type="hidden" name="programmeName" value="<%=programmeName%>">
									<div class="form-group">
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
							<input type="submit" class="btn btn-primary" value="Submit"/>
						</div>
						</fieldset>
						</form>
					</div>

				</div>
			</div>

		</div>
	</div>
	</div>
</div>


</body>
</html>