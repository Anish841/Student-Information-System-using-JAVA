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
    function showAccept(){
    	document.getElementById("rmv").style="display:block";
    	document.getElementById("af").style="display:none";
    }
    </script>
</head>
<body>
<%
boolean fprofileFlag=false; 	//true indicates your friends profile else your profile

%>
<s:if test="%{friendid !=null && friendid !=''}">
<%fprofileFlag=true;System.out.println("is Friend is true");%>
</s:if>
<s:else>
<% System.out.println("is Friend is false");
fprofileFlag=false;
%>
	
</s:else>

<%@ include file="userHeader.jsp"%>

	

	<div class="container">
        <h1>
        <%if(!fprofileFlag){ %>
        <s:property value="#session.user.name"/> Profile</h1>
        <%}else{ %>
        <s:property value="user.name"/> Profile</h1>
        <%} %>
        <s:if test="%{checkFriend =='true'}">
       		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"
       		 data-whatever="<s:property value="user.name"/>">Send Message</button>
  		</s:if>
  	<hr>
	<div class="row">
      <!-- left column -->
     <%@ include file="userLeftNav.jsp"%>
      
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
        <h3>Profile info</h3>
        
        <form class="form-horizontal" role="form">
          <div class="form-group">
            <label class="col-lg-3 control-label">User ID:</label>
            <div class="col-lg-8">
            <%if(!fprofileFlag){ %>
              <input class="form-control"  disabled="disabled"  value="<s:property value="#session.user.userId"/>" type="text" name="userId">
              <%}else{ %>
              <input class="form-control"  disabled="disabled"  value="<s:property value="user.userId"/>" type="text" name="userId">
              <%} %>
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">User Name:</label>
            <div class="col-lg-8">
               <%if(!fprofileFlag){ %>
              <input class="form-control" disabled="disabled" value="<s:property value="#session.user.name"/>" type="text" name="name">
              <%}else{ %>
              <input class="form-control" disabled="disabled" value="<s:property value="user.name"/>" type="text" name="name">
              <%} %>
            </div>
          </div>
           <div class="form-group">
            <label class="col-lg-3 control-label">Term Details:</label>
            <div class="col-lg-8">
            <%if(!fprofileFlag){ %>
              <input class="form-control" disabled="disabled" value="<s:property value="#session.user.term"/>" type="text" name="name">
              <%}else{ %>
               <input class="form-control" disabled="disabled" value="<s:property value="user.term"/>" type="text" name="name">
              <%} %>
            </div>
          </div>
            <div class="form-group">
            <label class="col-lg-3 control-label">Gender:</label>
            <div class="col-lg-8">
            <%if(!fprofileFlag){ %>
              <input class="form-control" disabled="disabled" value="<s:property value="#session.user.gender"/>" type="text" name="name">
               <%}else{ %>
               	 <input class="form-control" disabled="disabled" value="<s:property value="user.gender"/>" type="text" name="name">
                <%} %>
            </div>
          </div>
          
          <div class="form-group">
            <label class="col-lg-3 control-label">Address:</label>
            <div class="col-lg-8">
            <%if(!fprofileFlag){ %>
              <input class="form-control" disabled="disabled" value="<s:property value="#session.user.address"/>" type="text" name="name">
              <%}else {%>
              <input class="form-control" disabled="disabled" value="<s:property value="user.address"/>" type="text" name="name">
              <%} %>
            </div>
          </div>
           <div class="form-group">
            <label class="col-lg-3 control-label">Mobile:</label>
            <div class="col-lg-8">
            <%if(!fprofileFlag){ %>
              <input class="form-control" disabled="disabled" value="<s:property value="#session.user.mobno"/>" type="text" name="name">
                <%}else {%>
                 <input class="form-control" disabled="disabled" value="<s:property value="user.mobno"/>" type="text" name="name">
                <%} %>
            </div>
          </div>
           <div class="form-group">
            <label class="col-lg-3 control-label">DOB:</label>
            <div class="col-lg-8">
            <%if(!fprofileFlag){ %>
              <input class="form-control" disabled="disabled" value="<s:property value="#session.user.dob"/>" type="text" name="name">
              <%}else{ %>
               <input class="form-control" disabled="disabled" value="<s:property value="user.dob"/>" type="text" name="name">
              <%} %>
            </div>
          </div>

          <div class="form-group">
            <label class="col-lg-3 control-label">Email:</label>
            <div class="col-lg-8">
            <%if(!fprofileFlag){ %>
              <input class="form-control" disabled="disabled" value="<s:property value="#session.user.emailId"/>" type="text" name="email">
               <%}else{ %>
               <input class="form-control" disabled="disabled" value="<s:property value="user.emailId"/>" type="text" name="email">
               <%} %>
            </div>
          </div>
          
           <div class="form-group">
            <label class="col-lg-3 control-label">Interest:</label>
            <div class="col-lg-8">
             <%if(!fprofileFlag){ %>
              <input class="form-control" disabled="disabled" value="<s:property value="#session.user.interest"/>" type="text" name="email">
               <%}else{ %>
                 <input class="form-control" disabled="disabled" value="<s:property value="user.interest"/>" type="text" name="email">
                 <%} %>
            </div>
          </div>
           
          <div class="form-group">
            <label class="col-md-3 control-label"></label>
            <div class="col-md-8">
            <%if(!fprofileFlag){ %>
            	<a href="editProfileAction" class="btn btn-primary">Edit Profile</a>
            <%}else{ %>
			<s:if test="%{checkFriend =='true'}">
           			 <a id="rmv" style="display: none;" href="removeFriendAction?uid1=<s:property value="#session.user.userId"/>&uid2=<s:property value="user.userId"/>" class="btn btn-primary">Remove Friend</a>
            		<a id="af" style="display:block;" href="#" onclick="showAccept();" class="btn btn-primary" >Accept Friend</a>
            </s:if>
            <s:else>
            	<a href="addFriendAction?uid1=<s:property value="#session.user.userId"/>&uid2=<s:property value="user.userId"/>" class="btn btn-primary">Add Friend</a>
            </s:else> 
            <%} %>
            </div>
          </div>
          
      
   
<hr></form></div>


            <div id="push"></div>
        </div>
        
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog">
<div class="modal-content">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title" id="exampleModalLabel">Send short messages that will notify user.</h4>
  </div>
  <div class="modal-body">
    <form action="sendMessageAction" id="sendMessageForm">
      <div class="form-group">
        <label for="recipient-name" class="control-label">Recipient:</label>
        <input type="text" class="form-control" id="recipient-name" disabled="disabled">
      </div>
      <div class="form-group">
        <label for="message-text" class="control-label">Message:</label>
        <textarea class="form-control" id="message" name="message"></textarea>
      </div>
      <input type="hidden" name="friendid" value="<s:property value="friendid"/>"/>
    </form>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <button type="button" class="btn btn-primary" id="sendMessageButton">Send message</button>
  </div>
</div>
</div>
</div>
        
        <script src="/plugins/bootstrap-select.min.js"></script>
        <script src="/codemirror/jquery.codemirror.js"></script>
        <script src="/beautifier.js"></script>
        <script>
          (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
          (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
          m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
          })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
          ga('create', 'UA-40413119-1', 'bootply.com');
          ga('send', 'pageview');
        </script>
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
        
            $('.tw-btn').fadeIn(3000);
            $('.alert').delay(5000).fadeOut(1500);
            
            $('#sendMessageButton').click(function(){
            	
            	var msg=$("#message").val();
            	if(msg==null || msg=='')
            	{
            		return false;
            	}
            	$("#sendMessageForm").submit();
            });
            
            $('#exampleModal').on('show.bs.modal', function (event) {
            	  var button = $(event.relatedTarget); // Button that triggered the modal
            	  var recipient = button.data('whatever'); // Extract info from data-* attributes
            	  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
            	  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
            	  var modal = $(this);
            	  modal.find('.modal-title').text('Send Quick Message to ' + recipient);
            	  modal.find('#recipient-name').val(recipient);
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
            $(appendSelector).after('<div class="alert alert-info alert-block affix" id="msgBox" style="z-index:1300;margin:14px!important;">'+msg+'</div>');
            $('.alert').delay(3500).fadeOut(1000);
        }
        </script>
       
        <div id="completeLoginModal" class="modal hide">
            <div class="modal-header">
                <a href="#" data-dismiss="modal" aria-hidden="true" class="close">×</a>
                 <h3>Do you want to proceed?</h3>
            </div>
            <div class="modal-body">
                <p>This page must be refreshed to complete your login.</p>
                <p>You will lose any unsaved work once the page is refreshed.</p>
                <br><br>
                <p>Click "No" to cancel the login process.</p>
                <p>Click "Yes" to continue...</p>
            </div>
            <div class="modal-footer">
              <a href="#" id="btnYes" class="btn danger">Yes, complete login</a>
              <a href="#" data-dismiss="modal" aria-hidden="true" class="btn secondary">No</a>
            </div>
        </div>
        <div id="forgotPasswordModal" class="modal hide">
            <div class="modal-header">
                <a href="#" data-dismiss="modal" aria-hidden="true" class="close">×</a>
                 <h3>Password Lookup</h3>
            </div>
            <div class="modal-body">
                      
                  <div class="control-group">
                      <label class="control-label" for="inputEmail">Email</label>
                      <div class="controls">
                          <input name="_csrf" id="token" value="Aglpt14W-uEaFmjQ7k_AasvEAaUg2SS727SE" type="hidden">
                          <input name="email" id="inputEmail" placeholder="you@youremail.com" required="" type="email">
                          <span class="help-block"><small>Enter the email address you used to sign-up.</small></span>
                      </div>
                  </div>
                  
            </div>
            <div class="modal-footer pull-center">
                <a href="#" data-dismiss="modal" aria-hidden="true" class="btn">Cancel</a> 	
            	<a href="#" data-dismiss="modal" id="btnForgotPassword" class="btn btn-success">Reset Password</a>
            </div>
            
        </div>
        <div id="upgradeModal" class="modal hide">
            <div class="modal-header">
                <a href="#" data-dismiss="modal" aria-hidden="true" class="close">×</a>
                 <h4>Would you like to upgrade?</h4>
            </div>
            <div class="modal-body">
                  <p class="text-center"><strong></strong></p>
                  <h1 class="text-center">$4<small>/mo</small></h1>
                  <p class="text-center"><small>Unlimited plys. Unlimited downloads. No Ads.</small></p>
                  <p class="text-center"><img src="/assets/i_visa.png" alt="visa" width="50"> <img src="/assets/i_mc.png" alt="mastercard" width="50"> <img src="/assets/i_amex.png" alt="amex" width="50"> <img src="/assets/i_discover.png" alt="discover" width="50"> <img src="/assets/i_paypal.png" alt="paypal" width="50"></p>
            </div>
            <div class="modal-footer pull-center">
                <a href="/upgrade" class="btn btn-block btn-huge btn-success"><strong>Upgrade Now</strong></a>
            	<a href="#" data-dismiss="modal" class="btn btn-block btn-huge">No Thanks, Maybe Later</a>
            	
            </div>
        </div>
        <div id="contactModal" class="modal hide">
            <div class="modal-header">
                <a href="#" data-dismiss="modal" aria-hidden="true" class="close">×</a>
                <h3>Contact Us</h3>
                <p>suggestions, questions or feedback</p>
            </div>
            <div class="modal-body">
                  <form class="form form-horizontal" id="formContact">
                      <input name="_csrf" id="token" value="Aglpt14W-uEaFmjQ7k_AasvEAaUg2SS727SE" type="hidden">
                      <div class="control-group">
                          <label class="control-label" for="inputSender">Name</label>
                          <div class="controls">
                              <input name="sender" id="inputSender" class="input-large" placeholder="Your name" type="text">
                          </div>
                      </div>
                      <div class="control-group">
                          <label class="control-label" for="inputMessage">Message</label>
                          <div class="controls">
                              <textarea name="notes" rows="5" id="inputMessage" class="input-large" placeholder="Type your message here"></textarea>
                          </div>
                      </div>
                      <div class="control-group">
                          <label class="control-label" for="inputEmail">Email</label>
                          <div class="controls">
                              <input name="email" id="inputEmail" class="input-large" placeholder="you@youremail.com (for reply)" required="" type="text">
                          </div>
                      </div>
                  </form>
            </div>
            <div class="modal-footer pull-center">
                <a href="#" data-dismiss="modal" aria-hidden="true" class="btn">Cancel</a>     
                <a href="#" data-dismiss="modal" aria-hidden="true" id="btnContact" role="button" class="btn btn-success">Send</a>
            </div>
        </div>

	
	<script src="/plugins/bootstrap-pager.js"></script>
</div>
	<!-- /container -->
</body>
</html>