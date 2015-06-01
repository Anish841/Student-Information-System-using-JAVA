<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.iiitb.sis.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
	<link rel="stylesheet" href="css/bootstrap-multiselect.css" type="text/css"/>
	<script type="text/javascript">
    $(document).ready(function() {
        $('#interest').multiselect({
        	buttonWidth: '450px',
        		dropRight: true
        });
    });
    </script>
</head>
<body>
<%
	User u=(User)session.getAttribute("user");
	String interest=u.getInterest();
	String intarr[]=interest.split(",");
	int strln=intarr.length;
	System.out.println("interst===>"+interest);
	ArrayList<Interest> intList=(ArrayList<Interest>)request.getAttribute("interestList");
	int intlen=0;
	if(intList!=null)
	intlen=intList.size();
%>

<%@ include file="userHeader.jsp"%>


	<div class="container">
        <h1>My Profile</h1>
  	<hr>
	<div class="row">
      <!-- left column -->
       <div class="col-sm-6 col-md-3">
				<div class="thumbnail">
					<img src="image1Action?userId=<s:property value="#session.user.userId"/>" alt="...">
					<div class="caption">
						Upload different pic:
						<form action="editPhotoAction" enctype="multipart/form-data" method="post" >
						<s:file name="pic"/>
						<input type="submit" class="btn btn-primary" value="Change">
						</form>
					</div>
				</div>
		</div>
      
      <!-- edit form column -->
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
        <h3>Personal info</h3>
        
        <form class="form-horizontal" role="form" name="editProfileForm" action="submitProfileAction" id="editProfileForm">
          <div class="form-group">
            <label class="col-lg-3 control-label">User ID:</label>
            <div class="col-lg-8">
              <input class="form-control" value="<s:property value="#session.user.userId"/>" disabled="disabled" type="text" name="userId" id="userId">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Name:</label>
            <div class="col-lg-8">
              <input class="form-control" value="<s:property value="#session.user.name"/>" disabled="disabled" type="text" name="name" id="name">
            </div>
          </div>

          <div class="form-group">
            <label class="col-lg-3 control-label">Email:</label>
            <div class="col-lg-8">
              <input class="form-control" value="<s:property value="#session.user.emailId"/>" type="text" name="email" id="email">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Mobile:</label>
            <div class="col-lg-8">
              <input class="form-control" value="<s:property value="#session.user.mobno"/>" type="text" name="mobno" id="mobno">
            </div>
          </div>
          
 
          <div class="form-group">
            <label class="col-md-3 control-label">Password:</label>
            <div class="col-md-8">
              <input class="form-control" value="<s:property value="#session.user.password"/>" type="password" name="password" id="password1">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Renter Password:</label>
            <div class="col-md-8">
              <input class="form-control" value="<s:property value="#session.user.password"/>" type="password" name="repassword" id="repassword">
            </div>
          </div>
          
         
            
          <div class="form-group">
            <label class="col-lg-3 control-label">Interest:</label>
            <div class="col-lg-6">
              <div class="ui-select">
                <select id="interest" class="form-control" multiple="multiple" name="interest" id="interest">
                	<%for(int i=0;i<intlen;i++){ 
                		Interest intl=intList.get(i);
                		boolean flag=false;
                		for(int j=0;j<strln;j++){
                			if(intl.getInterestName().equalsIgnoreCase(intarr[j])){
                			flag=true;
                			}} %>
                		<option value="<%=intl.getInterestId()%>" <%if(flag){ %>selected="selected"<%} %>><%=intl.getInterestName() %></option>
                			 <%} %>
                </select>
              </div>
            </div>
          </div>
          
            
          <div class="form-group">
            <label class="col-md-3 control-label"></label>
            <div class="col-md-8">
              <input class="btn btn-primary" value="Save Changes" id="save" type="button">
              <span></span>
              
            </div>
          </div>
          
      
   
<hr></form></div>


            <div id="push"></div>
        </div>
        
        
        <script src="/plugins/bootstrap-select.min.js"></script>
        <script src="/codemirror/jquery.codemirror.js"></script>
        <script src="/beautifier.js"></script>
       
        <script>
        jQuery.fn.shake = function(intShakes, intDistance, intDuration, foreColor) {
            this.each(function() {
                if (foreColor && foreColor!="null") {
                    $(this).css("color",foreColor); 
                }
                $(this).css("position","relative"); 
                for (var x=1; x<=intShakes; x++) {
                $(this).animate({left:(intDistance*-1)}, (((intDuration/intShakes)/4)))
                .animate({left:intDistance}, ((intDuration/intShakes)/2))
                .animate({left:0}, (((intDuration/intShakes)/4)));
                $(this).css("color",""); 
            }
          });
        return this;
        };
        </script>
        <script>
        $(document).ready(function() {
        	$('.alert').delay(3500).fadeOut(1000);
        	
        	
            $('#save').click(function(){
            	var email=$("#email").val();
            	var filter = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
            	var filterM = /^[0-9-+]+$/;
            	var password=$("#password1").val();
            	var repassword=$("#repassword").val();
            	var mobno=$("#mobno").val();
            	if(email=="" || email==" " || email==null){
            		 $('#email').focus(); $('#save').shake(4,6,700,'#CC2222');
            		 prependAlert("#spacer","Please enter Email ID");
    				return false;
    			}
            	if (!filter.test(email)) {
            		 $('#email').focus(); $('#save').shake(4,6,700,'#CC2222');
            		 prependAlert("#spacer","Please enter Valid Email ID");
            		return false;
            	}
            	if(mobno=="" || mobno==" " || mobno==null){
           		 $('#mobno').focus(); $('#save').shake(4,6,700,'#CC2222');
           		 prependAlert("#spacer","Please enter Mobile Number");
   				return false;
   				}
            	if (!filterM.test(mobno)) {
           		 $('#mobno').focus(); $('#save').shake(4,6,700,'#CC2222');
           		 prependAlert("#spacer","Please enter Valid Mobile Number");
           			return false;
           		}
    			if(password=="" || password==" " || password==null){
           		 $('#password1').focus(); $('#save').shake(4,6,700,'#CC2222');
        		 prependAlert("#spacer","Please enter password");
				 return false;
				}
    			if(repassword=="" || repassword==" " || repassword==null){
              		 $('#repassword').focus(); $('#save').shake(4,6,700,'#CC2222');
           		 prependAlert("#spacer","Please enter repassword");
   				 return false;
   				}
    			if(password!=repassword){
             		 $('#repassword').focus(); $('#password').focus(); $('#save').shake(4,6,700,'#CC2222');
          		 prependAlert("#spacer","Password and repassword should be same.");
  				 return false;
  				}
    			$("#editProfileForm").submit();
    			return true;
            });
           
        });
        $.fn.serializeObject = function()
        {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function() {
                if (o[this.name] !== undefined) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        };
        var prependAlert = function(appendSelector,msg){
            $(appendSelector).after('<div class="alert alert-danger alert-dismissable"> <a class="panel-close close" data-dismiss="alert">×</a>  <i class="fa fa-coffee"></i>'+msg+'</div>');
            $('.alert').delay(3500).fadeOut(1000);
        }
        
        </script>
       
	
	<script src="/plugins/bootstrap-pager.js"></script>
</div><br><br><br><br>
	<!-- /container -->
</body>
</html>