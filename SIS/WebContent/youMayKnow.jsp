<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="icon" href="../../favicon.ico">

<title>You May Know</title>

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
	function addFriend(id){
	//	alert(id);
	document.getElementById('userId').value=id;
	document.getElementById('mayKnow').action="knowAddFriend";
	$('#mayKnow').submit();
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
						<h3>People You May know</h3>
						<s:if test="hasActionErrors()">
			<div class="alert alert-danger alert-dismissable">
				 <a class="panel-close close" data-dismiss="alert">×</a> 
				  <i class="fa fa-coffee"></i>
				  <s:actionerror/>
			</div>
		</s:if>
		<s:if test="hasActionMessages()">
			 <div class="alert alert-info alert-dismissable">
				 <a class="panel-close close" data-dismiss="alert">×</a> 
				  <i class="fa fa-coffee"></i>
				  <s:actionmessage/>
			</div>
		</s:if>
						<div> 
														
									<form class="form-horizontal"  method="post" enctype="multipart/form-data" action="youMayKnow" id="mayKnow">
									<div class="form-group">
										<label class="col-sm-3 control-label" for="select">Enter Student ID:</label>
										<div class="col-lg-8">
											<input name="userId" type="text" id="userId"/>
											<input type="submit" value="submit">
										</div>
									</div>
									</form>
									<table class="table table-striped table-hover">
								<thead>
								<tr>
									<th>Sno.</th>
									<th>Student Id</th>
									<th>Name</th>
									<th>Sem</th>
									<th></th>
								</tr>
								</thead>
  								<tbody>
								<s:iterator value="userList" var="list">
									<tr>
									<td><s:property value="#list.sno"/></td>
									<td><s:property value="#list.student_id"/></td>
									<td><s:property value="#list.name"/></td>
									<td><s:property value="#list.term"/></td>
									<td><input type="button" value="Add Friend" onclick="addFriend('<s:property value="#list.student_id"/>');"></td>
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
	<!-- /container -->
</body>
</html>
