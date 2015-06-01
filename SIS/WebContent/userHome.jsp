<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="icon" href="../../favicon.ico">

<title>User Profile</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/bootstrapblueswatch.css">

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
#noti {
	width: 300px !important;
	height: 400px !important;
}
</style>
</head>
<body>
<%@ include file="userHeader.jsp"%>

	<div class="container">
		<div class="row">
			<%@ include file="userLeftNav.jsp" %>
			<div class="col-sm-10 col-md-6">
				<div class="thumbnail">
					<div class="caption">
						<h3><s:property value="#session.user.name"/>'s Home</h3>
						<p></p>
						<p>Welcome to Student Information System.</p>
						<h3>Tips:</h3>
						<ul>
							<li>Click "Your Name" on top navigation menu to access your profile</li>
							<li>Type the roll number of the student you wish to search for in the search textbox in the menu</li>
							<li>Use the five hyperlinks (Home, Grades, Subjects, Enrollment and My Friends) to navigate to the respective pages.</li>
							<li>Announcements matching your interests are displayed.Hover the mouse pointer over the section to stop auto-scrolling.</li>
						</ul>
						
					</div>
				</div>
			</div>
			<div class="col-sm-3 col-md-3">
			<div class="thumbnail">
				<%@ include file="campusNews.jsp" %>
				</div>
			</div>
		</div>

	</div>
	<!-- /container -->
</body>
</html>