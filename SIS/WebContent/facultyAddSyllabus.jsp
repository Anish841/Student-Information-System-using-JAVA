<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Syllabus</title>
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
</script>
<style type="text/css">
.modalBlock {
	display: block;
	position: relative;
}
</style>

<script language="javascript">
function hello()
{
	document.syllabus_form.action="GetSyllabus"+".action";
    document.syllabus_form.submit();
}
	
</script>

</head>
<body>

	<div id="wrapper">
	<%@ include file="facultyLeftNav.jsp"%>
		<%@ include file="adminHeader.jsp"%>
		<div class="row">
			<div class="modal modalBlock">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Please Enter the Syllabus</h4>
						</div>
						<div class="modal-body">
							<s:form class="form-horizontal" enctype="multipart/form-data" method="post" action="InputSyllabus" id="syllabus_form">
								<fieldset>
									<div class="form-group">
										<label for="select" class="col-sm-2 control-label">Select
											Subject</label>
										<div class="col-lg-8">
											<!--<select  id="sub" name="Subject" onchange="hello();">
												<option default>Select A Subject</option>
												<s:iterator value="subjectLists" status="status">
														<option value="<s:property value="subjectCode"/>"><s:property value="subjectName"/></option>
												</s:iterator>
											</select>-->
											<!--<s:property value="temp_subject"/>-->
											<select class="form-control" name="Subject" id="prog" onchange="hello();">
											<s:if test="%{temp_subject==null}"><option default>Select A Subject</option></s:if>
											<s:elseif test="%{temp_subject!=null}"><option default value="<s:property value="temp_subject_id"/>"><s:property value="temp_subject"/></option></s:elseif>
												<s:iterator value="subjectLists" status="status">
														<option value="<s:property value="subjectCode"/>"><s:property value="subjectName"/></option>
												</s:iterator>
											</select>
											
										</div>
									</div>
								
										
									
									<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput">Enter Syllabus</label>
										<div class="col-sm-10">
											<textarea name="Syllabus" rows="5"
												class="form-control" ><s:if test="%{s.syllabus_type==0}"><s:property value="%{s.syllabus_text}"/></s:if></textarea>
											</div>
									</div>
<!-- 	<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput">Enter Syllabus</label>
											<s:if test="%{s.syllabus_text}">
										<div class="col-sm-10">
											<textarea placeholder="%{s.syllabus_text}" name="Syllabus" rows="5"
												class="form-control" ></textarea>
											</div>
											</s:if>
									</div> -->
									 <div class="form-group">
									 <div class="col-sm-2"></div>
									 <div class="col-sm-10">
										<s:if test="%{s.syllabus_type==1}">
											<a href="<s:property value="%{s.syllabus_text}"/>">Download <s:property value="%{s.subjectName}"/> Syllabus</a>
										</s:if>
									</div>
									 </div>
									 <div class="form-group">
										<label class="col-sm-2 control-label" for="textinput">OR</label>
										<div class="col-sm-10">
											<s:file theme="simple" name="fileUpload" cssStyle="margin-top: 10px" label="SyllabusPDF"></s:file>
										</div>
									</div>

									 
								</fieldset>
								<br>
								<br>
								<div class="modal-footer">
									<input type="reset" class="btn btn-default" /> 
									<input type="submit" class="btn btn-primary" value="Add/Update Syllabus" />
								</div>
								
								
							</s:form>
							
							<s:property value="errormsg"/>
						</div>
					</div>
				</div>
			
			</div>
		</div>
	</div>



</body>
</html>