<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Assigned Faculty</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrapswatch.css">
<link href="css/simple-sidebar.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
	
	function faculty() {
		$('#faculty_id').submit();
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
							<h4 class="modal-title">Select Faculty</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="faculty_id" method="post" enctype="multipart/form-data" action="getAssignedSubject">
								<fieldset>
									<div class="form-group">

										<label class="col-sm-2 control-label" for="select">Faculty ID:</label>
										<div class="col-lg-8">
											<select class="form-control" name="Faculty_id" onchange="faculty();">
												<option default><s:property value="defaultValue" /></option>
												<s:iterator value="user" status="status">
													<option value="<s:property value="userId"/>">
														<s:property value="name" />
													</option>
												</s:iterator>
											</select>
										</div>
									</div>
										<table class="table table-hover">
											<tr>
												
												<th>Subject Id</th>
												<th>Subject Name</th>
											</tr>
											<s:iterator value="subjectInfo" status="status">
												<tr>
													<%-- <td><s:property value="faculty_name" /></td> --%> 
													<td><s:property value="subjectCode" /></td>
													<td><s:property value="subjectName" /></td>
													
												</tr>
											</s:iterator>
										</table>
										<s:property value="msg" />
									</div>
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
