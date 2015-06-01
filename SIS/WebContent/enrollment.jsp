<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrapswatch.css">
<link href="css/simple-sidebar.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap-multiselect.css"
	type="text/css" />
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('.alert').delay(3500).fadeOut(1000);
        $('#subject').multiselect({
        	buttonWidth: '450px',
        		dropRight: true
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
<%@ include file="userHeader.jsp"%>
<div class="container">
		<div class="row">
		
		<%@ include file="userLeftNav.jsp"%>
		<div class="col-sm-7 col-md-8">
				<div class="thumbnail">
				<div class="caption">
						<h3>Dear <s:property value="#session.user.name"/> </h3>
						<h3>You can Enroll Subjects</h3>
			<div class="modal modalBlock">
				<div class="modal-dialog">
					<div class="modal-content">
						
						<div class="modal-body">
							<form class="form-horizontal" action="AddSelectedSubjects" enctype="multipart/form-data" method="post">
								<fieldset>
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
										<div class="form-group">
										<label class="col-sm-2 control-label" >Select Subjects to Enroll</label>
										<div class="col-sm-2">
										<select name="subject" id="subject" multiple="multiple">
											<s:iterator value="temp_subject" >
	           								<option value="<s:property />"><s:property />
	           								</option>
	           								</s:iterator>
	           							</select>
	           								</div>
	        						</div>
									<div class="modal-footer">
							 <input type="submit" class="btn btn-primary" value="Enroll"/>
						</div>
								</fieldset>
							</form>
						</div>
						</div>
					</div>
				</div>
			</div></div>
			</div>
		</div>
	</div>



</body>
</html>