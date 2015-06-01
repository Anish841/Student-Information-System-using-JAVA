<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ taglib prefix="s" uri="/struts-tags"%>
<div class="col-sm-6 col-md-3">
				<div class="thumbnail">
				
				<s:if test="%{friendid !=null && friendid !='' && (checkNoFriend==null || checkNoFriend=='')}">
					<img src="image1Action?userId=<s:property value="user.userId"/>" alt="No Image Available.">
				</s:if>
				<s:else>
					<img src="image1Action?userId=<s:property value="#session.user.userId"/>" alt="No Image Available.">
				</s:else>
					
				
					<div class="caption">
						<p>
						Last Logged On:<s:property value="#session.user.lastLoggedOn"/>
						</p>
					</div>
				</div>
</div>