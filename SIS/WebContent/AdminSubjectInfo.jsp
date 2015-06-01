<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Faculty Info</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrapswatch.css">
<link href="css/simple-sidebar.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap-multiselect.css"
	type="text/css" />
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#sub').multiselect({
			buttonWidth : '450px',
			 dropRight : true 
		});
	});
	

	function faculties() {
		$('#sub_id').submit();
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
			<div class="modal modalBlock">
				<div class="modal-dialog">
					<div class="modal-content">
						<!-- <div class="modal-header">
							<h4 class="modal-title">Select Subject</h4>
						</div> -->
						<div class="modal-body">
							<s:form class="form-horizontal" enctype="multipart/form-data" method="post" id="sub_id" action="GetFacultyFromSubject">
								<fieldset>
									<div class="form-group">
										<label for="select" class="col-sm-2 control-label">Select Subject</label>
										<div class="col-lg-8">
											<select class="form-control" id="sub" name="Subject_id" onchange=" faculties()">
												<option default><s:property value="defaultvalue"/></option>
												<s:iterator value="subjectLists" status="status">
														<option value="<s:property value="subjectCode"/>"><s:property value="subjectCode"/> - <s:property value="subjectName"/></option>
												</s:iterator>
											</select>
										</div>
									</div>
									
								<div>
								
								<table class="table table-hover">
											 <tr>
												<th>Faculty ID</th>
												<th>Faculty Name</th>
												<th>Email ID</th>
												<th>Mobile No.</th>
												<th>Address</th>
											</tr> 
											<s:iterator value="FacultyLists" status="status">
												<tr>
													<td><s:property value="userId" /></td>
													<td><s:property value="name" /></td>
													<td><s:property value="emailId" /></td>
													<td><s:property value="mobno" /></td>
													<td><s:property value="address" /></td>
												</tr>
											</s:iterator>
										</table>
								
									<%-- <s:iterator value="FacultyLists" status="status">
														<s:property value="name"/> - <s:property value="emailId"/>
												</s:iterator> --%>
							
								</div>
<!-- 
									Text input
									<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput">Line
											2</label>
										<div class="col-sm-10">
											<input type="text" placeholder="Address Line 2"
												class="form-control">
										</div>
									</div>

									Text input
									<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput">City</label>
										<div class="col-sm-10">
											<input type="text" placeholder="City" class="form-control">
										</div>
									</div>

									Text input
									<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput">State</label>
										<div class="col-sm-4">
											<input type="text" placeholder="State" class="form-control">
										</div>

										<label class="col-sm-2 control-label" for="textinput">Postcode</label>
										<div class="col-sm-4">
											<input type="text" placeholder="Post Code"
												class="form-control">
										</div>
									</div>



									Text input
									<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput">Country</label>
										<div class="col-sm-10">
											<input type="text" placeholder="Country" class="form-control">
										</div>
									</div>
									 -->
									 

									 
								</fieldset>
								
								<!-- <div class="modal-footer">
									<input type="reset" class="btn btn-default" /> 
									<input type="submit" class="btn btn-primary" />
								</div> -->
								<div align="center">
									<s:property value="errormsg"/>
								</div>
								
								<br>
								<br>
							</s:form>
							
							
						</div>
						<br><br><br><br><br><br><br><br><br><br>
					</div>
				</div>
			
			</div>
			
		</div>
		
	</div>



</body>
</html>