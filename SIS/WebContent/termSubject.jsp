<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Term - Subject</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrapswatch.css">
<link href="css/simple-sidebar.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
	
	function students() {
		$('#programme').submit();
	}
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
			<div class="modal modalBlock">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Select Program</h4>
						</div>
						<div class="modal-body">
							<s:form class="form-horizontal" id="programme" method="post" enctype="multipart/form-data" action="getSubject">
								<fieldset>
									<div class="form-group">

										<label class="col-sm-2 control-label" for="select">Programme</label>
										<div class="col-lg-8">
											<select class="form-control" name="prog" onchange="students();">
												<option default><s:property value="message" /></option>
												<s:iterator value="programme" status="status">
													<option value="<s:property />">
												
														<s:property />
													</option>
												</s:iterator>
											</select>
										</div>
									</div>
										<table class="table table-hover" >
											<tr align="center">
												<td><b><u>Term</u></b></td>
												<td><b><u>Subject ID</u></b></td>
												<td><b><u>Subject Name</u></b></td>
											</tr>
											<s:iterator value="termsubject" status="status">
												<tr align="center">
													<td><s:property value="term_name" /></td>
													<td><s:property value="subject_id" /></td>
													<td><s:property value="subject_name" /></td>

												</tr>
											</s:iterator>
										</table>

								</fieldset>
							</s:form>
						</div>
						<div class="modal-footer"></div>
					</div>
				</div>

			</div>
		</div>



</body>
</html>