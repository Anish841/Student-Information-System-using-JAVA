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
			<%@ include file="userLeftNav.jsp"%>
			<div class="col-sm-10 col-md-6">
				<div class="thumbnail">
					<div class="caption">
						<h3>
							Dear
							<s:property value="#session.user.name" />
						</h3>
						<h3>Syllabus Of All Subjects</h3>
						<div>
							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<th>Course ID</th>
										<th>Syllabus</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="subjectLists" var="list">
										<tr>
											<td><s:property value="#list.subjectName" /></td>
											<td><s:if test="%{#list.syllabus_type==1}">
													<a href="<s:property value="%{#list.syllabus_text}"/>">Download
														<s:property value="%{s.subjectName}" /> Syllabus
													</a>
												</s:if> <s:else>
													<s:property value="#list.syllabus_text" />
											</s:else>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-3 col-md-3">
				<div class="thumbnail">
					<%@ include file="campusNews.jsp"%>
				</div>
			</div>
		</div>

	</div>
	<!-- /container -->
</body>
</html>