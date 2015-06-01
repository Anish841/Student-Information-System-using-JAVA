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
<script type="text/javascript">
	
	function Semester() {
		$('#sub_id').submit();
	}
</script>
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
						<h3>Dear <s:property value="#session.user.name"/> </h3>
						<h3>Your Grades</h3>
						<div> 
							<table class="table table-striped table-hover">
								<thead>
								<tr>
									<form class="form-horizontal" id="sub_id" method="post" enctype="multipart/form-data" action="GradesPerSemAllSub">
									<%ArrayList<Terms> termsList = (ArrayList<Terms>)(request.getAttribute("termList"));%>
									<th><h4>Semester</h4>
									<select class="form-control" name="term_id" id="term_id" onchange="Semester();">
									<%
									for(int i=0;termsList != null && i<termsList.size();i++){
										Terms t = termsList.get(i);
										if(t!=null && t.getTerm_id().equalsIgnoreCase((String)request.getAttribute("term_id"))){
									%>
											<option value="<%=t.getTerm_id()%>"selected="selected"><%=t.getTerm_name() %></option>
										<%}else{%>
											<option value="<%=t.getTerm_id()%>"><%=t.getTerm_name() %></option>
									<%}} %>
									</th>
									</form>
									<th>
									</th>
									</form>
									
								</tr>
								<tr>
									<th>Subject ID</th>
									<th>Subject</th>
									<th>Grades</th>
									<th>Pointer</th>
								</tr>
								</thead>
  								<tbody>
								<s:iterator value="gradesList" var="list">
									<tr>
									<td><s:property value="#list.subjectID"/></td>
									<td><s:property value="#list.subjectName"/></td>
									<td><s:property value="#list.grade"/></td>
									<td><s:property value="#list.pointer"/></td>
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
				<%@ include file="campusNews.jsp" %>
				</div>
			</div>
		</div>

	</div>
	<!-- /container -->
</body>
</html>
