<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SIS | Assign Grades</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrapswatch.css">
<link href="css/simple-sidebar.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap-multiselect.css"
	type="text/css" />
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>


<script type="text/javascript">
	$(document).ready(function() {	
		$('[name="name"]').multiselect({
			 disableIfEmpty: true
		});
		
		$("[name='headLine']").focus(function(){
			if($(".msg").is(":hidden") == false){
				$(".msg").hide();
			}			
		});
		
	});
	function validate(){
		if($('[name="name"]').val() == null){
			alert("please choose something.");
			return false;
		}
		return true;	
	}
</script>
<style type="text/css">
.modalBlock {
	display: block;
	position: relative;
}
.msg{
	color:blue;
	font-size:12px;
	display:block;
}
</style>
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
							<h4 class="modal-title">ASSIGN GRADES</h4>
						</div>
						
						<div class="modal-body">
						<s:if test="%{checkStudent =='true'}">
							<h3>No Student Enrolled For your courses.</h3>
						</s:if>
						<s:else>
						<s:actionmessage/>
						<s:if test="hasActionMessages()">
					    
						</s:if>
						
							<form class="form-horizontal" action="addGrades" name="studentsGrade"  method="post">
								<fieldset>
								<div id="spacer"></div>
								<s:if test="hasActionErrors()">
			 <div class="alert alert-info alert-dismissable">
				 <a class="panel-close close" data-dismiss="alert">×</a> 
				  <i class="fa fa-coffee"></i>
				  <s:actionerror/>
			</div>
		</s:if>		
								
								<s:set name="newchoice" value="newchoice"/>
								 	<div class="form-group">
										<label class="col-sm-4 control-label" for="textinput">Roll No</label>
										<div class="col-lg-8">
											
											 
											<s:select id="studentDisplayChoice" name="studentDisplayChoice"
							list="studentList" label="Student Name " class="form-control"
							onchange="onStudentChange()"></s:select>
										</div>
										<label class="col-sm-4 control-label" for="textinput">Course</label>
										<div class="col-lg-8">
											
											 
											 <s:select class="form-control" id="courseDisplayChoice" name="courseDisplayChoice" list="courseList"
							label="Course Name " onchange="onCourseChange()" ></s:select>
											
										</div>
										
										
										<label class="col-sm-4 control-label" align="left" for="textinput">Grade</label>
										<div class="col-lg-8">
											
											 
											 <s:select  class="form-control" id="gradeDisplayChoice" name="gradeDisplayChoice" list="gradeList" label="Grade  "
							></s:select>
										</div>
									</div>
									<div class="modal-footer">
							<input type="submit" value="Assign" class="btn btn-primary" />
						</div>
						</fieldset>
						</form>
								</s:else>
								
						</div>
						
					</div>
				</div>
			
			</div>
		</div>
	</div>

<script type="text/javascript">
		function onStudentChange() {
			document.studentsGrade.action = 'assignGrades.action';
			document.studentsGrade.submit();
		}
	</script>
	
	<script type="text/javascript">
		function onCourseChange() {
			document.studentsGrade.action = 'courseChanged.action';
			document.studentsGrade.submit();
		}
	</script>
	

</body>
</html>