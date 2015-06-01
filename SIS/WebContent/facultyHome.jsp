<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrapswatch.css">
<link href="css/simple-sidebar.css" rel="stylesheet">
<script
	src="js/jquery-1.10.2.min.js"></script>
<script
	src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap-multiselect.css"
	type="text/css" />
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#sport').multiselect({
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
</head>
<body">

	<div id="wrapper">
		<%@ include file="facultyLeftNav.jsp"%>
		<%@ include file="adminHeader.jsp"%>
		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<div class="jumbotron">
					<table align="center" width="70%">
						<tr>
							<td><h2 class="text-primary">
									<b>Welcome </b> <s:property value="#session.user.name"/>
								</h2></td>
						</tr>
						<tr>
							<td><h4>
									<b>User ID:</b><s:property value="#session.user.userId"/>
								</h4></td>
							<td><h4>
									<b>Last Logged on:</b><s:property value="#session.user.lastLoggedOn"/>
								</h4></td>
						</tr>
						<tr>
							<td colspan="2"><h5>This will provide you functionalities of all faculty operations.</h5></td>
						</tr>
						<tr>
							<td colspan="2"><h5>Select the functionalities on left navigation panel to
							proceed.</h5></td>
						</tr>
					</table>
				</div>
				
			</div>
		</div>
		<!-- /#page-content-wrapper -->
	</div>



</body>
</html>