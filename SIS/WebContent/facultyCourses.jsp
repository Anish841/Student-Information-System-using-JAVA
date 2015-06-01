<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
<style type="text/css">
.modalBlock {
	display: block;
	position: relative;
}

.msg {
	color: blue;
	font-size: 12px;
	display: block;
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
							<h4 class="modal-title">My Course List</h4>
						</div>
						<div class="modal-body">
							
							<table class="table table-striped table-hover ">
								<thead>
									<tr>
										<th>#</th>
										<th>Subject ID</th>
										<th>Subject Name</th>
										<th>Credits</th>
									</tr>
								</thead>
								<tbody>
								<%int i=1; %>
								<s:iterator value="fcourse">
									<tr>
										<td><%=i++%></td>
										<td><s:property value="subjectCode"/></td>
										<td><s:property value="subjectName"/></td>
										<td><s:property value="point"/></td>
									</tr>
									</s:iterator>
									
								</tbody>
							</table>


						</div>

					</div>
				</div>

			</div>
		</div>
	</div>


</body>
</html>