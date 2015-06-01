<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SIS | Assign Grades</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrapswatch.css">
<link href="css/simple-sidebar.css" rel="stylesheet">
<script
	src="js/jquery.js"></script>
<script
	src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap-multiselect.css"
	type="text/css" />
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$("[name='headLine']").focus(function(){
			if($(".msg").is(":hidden") == false){
				$(".msg").hide();
			}			
		});
	});
	
	function validate(){
		var headline = $("[name='headLine']").val();
		var body = $('[name="detailedNews"]').val();
		if($.trim(headline) == "" && $.trim(body) == ""){
			alert("please fill the form");
			return false;
		}
		return true;
	}	
</script>
<style type="text/css">
.modalBlock {
	display: block;
	position: relative;
}
.msg{
	color:blue;
	font-size:12px;
	display:block;
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
							<h4 class="modal-title">STATUS</h4>
						</div>
						<div class="modal-body">
								<fieldset>
									<div class="form-group">
									
										<div class="msg col-sm-2"></div>
										<div class="msg col-sm-10" id="message"><s:actionmessage/></div>
									
										
										

									<!-- Text input-->
									
										
										
									</div>
									<div class="modal-footer">
							 </div>
									</fieldset>
				
					</div>
				</div>
			
			</div>
		</div>
	</div>
</body>
</html>