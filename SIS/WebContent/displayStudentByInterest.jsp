<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student By Interest</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrapswatch.css">
<link href="css/simple-sidebar.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
	
	function students() {
		$('#interest').submit();
	}
</script>
<style type="text/css">
.modalBlock {
	display: block;
	position: relative;
	height:1000px;
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
							<h4 class="modal-title">Name:Anish Ratnawat &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RollNo:MT2014005</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="interest" method="post" enctype="multipart/form-data" action="getStudents">
								<fieldset>
									<div class="form-group">

										<label class="col-sm-3 control-label" for="select">Enter Interest=</label>
										<div class="col-lg-8">
											<select class="form-control" name="interestId" onchange="students();">
												<option default><s:property value="message" /></option>
												<s:iterator value="interestList" status="status">
													<option value="<s:property value="interestId"/>">
														<s:property value="interestName"/>
													</option>
												</s:iterator>
											</select>
										</div>
									</div>
									<s:if test="%{message !=null && message !=''}">
												<h4>Details of students having interest=<s:property value="message" />  is</h4>
									</s:if>
								
										<table class="table table-hover" style="overflow: auto;height:1px;">
											<tr align="center">
												<td><b><u>S.No.</u></b></td>
												<td><b><u>Student ID</u></b></td>
												<td><b><u>Student Name</u></b></td>
												<td><b><u>Photo</u></b></td>
												<td><b><u>Semester</u></b></td>
											</tr>
											<s:iterator value="studentList" status="status">
												<tr align="center">
													<td><s:property value="sno" /></td>
													<td><s:property value="student_id" /></td>
													<td><s:property value="name" /></td>
													<td><div style="width: 200px;"><div class="thumbnail"><img src="image1Action?userId=<s:property value="student_id"/>" alt="No Image Available."></div></div></td>
													<td><s:property value="term" /></td>
												</tr>
											</s:iterator>
										</table>

								</fieldset>
							</form>
						</div>
						<div class="modal-footer"></div>
					</div>
				</div>

			</div>
		</div>



</body>
</html>