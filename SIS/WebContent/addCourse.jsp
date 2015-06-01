<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ADD COURSE</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrapswatch.css">
<link href="css/simple-sidebar.css" rel="stylesheet">
<script
	src="js/jquery.min.js"></script>
<script
	src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap-multiselect.css"
	type="text/css" />
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>


<script type="text/javascript">
var debugScript = true;
function assignFaculty(){
	 document.course_form.submit();
}
</script>



<script language="javascript">
function hello()
{
	document.course_form.action="getTerm"+".action";
    document.course_form.submit();
}
function getSubjects(){
	document.course_form.action="getSubjectCourse";
    document.course_form.submit();
}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#facultyList').multiselect({
			buttonWidth : '250px',
			dropRight : true
		});
	});

	$(document)
			.ready(
					function() {
						$('#btnAdd')
								.click(
										function() {

											$("#tblData tbody")
													.append(
															"<tr>"
																	+ "<td><select class='form-control' name='subject' onchange=''><s:iterator value='subjectList'><option value='<s:property value='subjectCode' />'><s:property value='subjectName'/></option></s:iterator></select></td>"
																	+ "<td><select class='form-control' name='faculty'> <s:iterator value='facultyList' var='faculty'><option value='<s:property value='student_id' />'><s:property value='name' /></option></s:iterator></select></td>"
																	+ "<td><input type='text' placeholder='credits' class='form-control'></td>"
																	+ "</tr>");
										});

					});

	function get_prog() {
		var source;
		source = $("#prog : selected").text();
		alert(source);
		return source;

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
						<div class="modal-header">
							<h4 class="modal-title">Please fill up the form</h4>
						</div>
						<div class="modal-body">
							<s:form class="form-horizontal" action="addCourse" id="course_form">
								<fieldset>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput">Programme</label>
										<div class="col-lg-8">
											<select class="form-control" name="programme" id="prog" onchange="hello();">
												<option value = "<s:property value="%{temp_programme}"/>"><s:property value="%{temp_programme}" /></option>
												<s:iterator value="programmeList" var="programmes">
													<option value="<s:property value="#programmes"/>"><s:property
															value="#programmes" /></option>
												</s:iterator>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput">Term</label>
										<div class="col-lg-8">
										<select class="form-control" name="term" id="term" onchange="getSubjects();">
										<option value = "<s:property value="%{temp_term}"/>"><s:property value="%{temp_term}" /></option>
												<s:iterator value="termList" var="terms">
													<option value="<s:property value="#terms" />"><s:property
															value="#terms" /></option>
												</s:iterator>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<table class="table table-bordered" id="tblData">
											<thead>
												<tr>
													<th style="width: 300px;">Subject</th>
													<th style="width: 300px;">Faculty</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td><select class="form-control" name="subject" class="dropdown-menu">
															<s:iterator value="subjectList" var="subjects">
																<option value="<s:property value="subjectCode" />"><s:property
																		value="subjectName" /></option>
															</s:iterator>
													</select></td>
													<td><select class="form-control" name="faculty" class="dropdown-menu" multiple="multiple" id="facultyList">
															<s:iterator value="facultyList" var="faculty">
																<option value="<s:property value="student_id" />"><s:property
																		value="name" /></option>
															</s:iterator>
													</select></td>
												</tr>
											</tbody>
										</table>
									</div>
									<br><br><br>
								<%--	<div class="form-group">
										<div class="col-lg-1">
											<input id="btnAdd" onclick="Add()" value="ADD ROW" class="btn btn-default"/>
										</div>
									</div> --%>

									<div class="modal-footer">
										<input type="reset" value="     RESET    " class="btn btn-default"/> <input
											type="submit" value="     SUBMIT    " class="btn btn-default" onclick="return assignFaculty()" />
									</div>
									<input type="hidden" id="hdField_sub" name="course_sub"/>
									<input type="hidden" id="hdField_fac" name="course_fac" />
									<input type="hidden" id="hdField_cre" name="course_cre" />
								</fieldset>
							</s:form>
						</div>

					</div>
				</div>

			</div>
		</div>
	</div>



</body>
</html>

