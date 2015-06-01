<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SIS | Add News</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrapswatch.css">
<link href="css/simple-sidebar.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap-multiselect.css"
	type="text/css" />
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>

<script type="text/javascript">
	$(document).ready(function() {	
		$('[name="name"]').multiselect({
			 disableIfEmpty: true
		});
		
		$("[name='headLine']").focus(function(){
			if($(".msg").is(":hidden") == false){
				$(".msg").hide();
			}			
		});
		
	});
	function validate(){
		if($('[name="name"]').val() == null){
			alert("please choose something.");
			return false;
		}
		return true;	
	}
</script>
<style type="text/css">
.modalBlock {
	overflow: visible;
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
							<h4 class="modal-title">DELETE NEWS</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" action="deletenews" onsubmit="return validate()" method="post">
								<fieldset>
								<s:property value="newsList.newsId"/>
								 	<div class="form-group">
								 		<s:if test="%{msg == 'true'}">
											<div class="msg col-sm-2"></div>
											<div class="msg col-sm-10" id="message">Record(s) successfully deleted.</div>
										</s:if>
										<label class="col-sm-2 control-label" for="textinput">Headline</label>
										<div class="col-lg-8">
											
											 <select class="form-control" name="name" multiple="multiple">
											<s:iterator value="newsList" var="news">
												<option value='<s:property value="#news.newsId"/>' ><s:property value="#news.headLine"/></option>
											</s:iterator>
											</select>
										</div>
									</div>
								</fieldset>
						</div>
						<div class="modal-footer">
							<input type="submit" value="Delete" class="btn btn-primary" />
						</div>
						</form>
					</div>
				</div>
			
			</div>
		</div>
		<div></div>
	</div>
</body>
</html>