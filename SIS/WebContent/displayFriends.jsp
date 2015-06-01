<!DOCTYPE html>
	<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Display friends</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/bootstrapblueswatch.css">
    <!-- Custom CSS -->
 

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-typehead.js"></script>
</head>

<body>
<%@ include file="userHeader.jsp"%>

	<div class="container">
	
	 <!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">My Friends
                    <small>All friend List</small>
                </h1>
            </div>
        </div>
        
       
		<%ArrayList<User> userData=(ArrayList<User>)request.getAttribute("userData");
			 if(userData!=null && userData.size()>0){
				 for(int i=0;i<userData.size();){
					 int len=(userData.size())-i;
					 if(len>=4)len=4;
			 %>
			  <!-- Projects Row -->
        <div class="row">
        	<%for(int j=0;j<len;j++){
        		User u1=(User)userData.get(j+i);	 
        		%>
            <div class="col-md-3 portfolio-item">
                <a href="friendProfile?friendid=<%=u1.getUserId()%>">
                    <img class="img-responsive" src="image1Action?userId=<%=u1.getUserId() %>" alt="">
                </a>
                <h3>
                    <a href="friendProfile?friendid=<%=u1.getUserId()%>"><%=u1.getName() %></a>
                </h3>
              </div>
              <%} i=i+len;%>
        </div>
        <!-- /.row -->
			  <%}} %>
	 </div>
			    
</body>
</html>