<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Programme</title><link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrapswatch.css">
<link href="css/simple-sidebar.css" rel="stylesheet">
<script
	src="js/jquery-1.10.2.min.js"></script>
<script
	src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap-multiselect.css"
	type="text/css" />
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>

<link rel="stylesheet" href="css/bootstrap-multiselect.css"
	type="text/css" />
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
<script src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#example1').datepicker({
			format : "dd/mm/yyyy"
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
<body>
	<div id="wrapper">

		<%@ include file="adminLeftNav.jsp"%>
		<%@ include file="adminHeader.jsp"%>

		<div class="row">
		
		<div class="col-md-9 personal-info">
      	<div id="spacer"></div>
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
		
			<div class="modal modalBlock">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Add Interest</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" action="AddInterest" 
								enctype="multipart/form-data" method="post">
								<fieldset>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput" required>Interest Name</label>
										<div class="col-sm-10">
											<input type="text" placeholder="Interest Name" name="interestName"
												class="form-control" required>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="textinput" required>Interest Details</label>
										<div class="col-sm-10">
											<textarea name="intDetails" rows="5"
												class="form-control" ></textarea>
										</div>
									</div>
									
									
							</div>
						<div class="modal-footer">
							<input type="submit" class="btn btn-primary" value="Submit"/>
						</div>
						</fieldset>
						</form>
					</div>

				</div>
			</div>

		</div>
	</div>
	</div>
</div>


</body>
</html>