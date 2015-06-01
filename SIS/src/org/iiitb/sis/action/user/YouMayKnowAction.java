package org.iiitb.sis.action.user;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.iiitb.sis.dao.FriendsDAO;
import org.iiitb.sis.dao.impl.AddFriendImpl;
import org.iiitb.sis.dao.impl.FriendsDAOImpl;
import org.iiitb.sis.dao.impl.NotificationDAOImpl;
import org.iiitb.sis.model.Student;
import org.iiitb.sis.model.User;

import com.opensymphony.xwork2.ActionSupport;

public class YouMayKnowAction extends ActionSupport{

	private String userId;
	private ArrayList<Student> userList;
	
	public ArrayList<Student> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<Student> userList) {
		this.userList = userList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getAlluser(){
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		User user = (User)session.getAttribute("user");
		AddFriendImpl af=new AddFriendImpl();
		String birthPlace=af.getUserBirthPlace(userId);
		System.out.println(birthPlace);
		userList=af.getStudents(birthPlace,user.getUserId());
		return "success";
	}
	
	public String knowAddFriend(){
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		User u=(User)session.getAttribute("user");
		FriendsDAO fd=new FriendsDAOImpl();
		boolean f=fd.addFriend(u.getUserId(), userId);
		getAlluser();
		if(f){
			String msg=u.getName()+" has sent you friend Request";
			String url="searchAction?friendid="+u.getUserId();
			NotificationDAOImpl n=new NotificationDAOImpl();
			n.notifyUser(userId, msg, "FRND_REQ", url);
			addActionMessage("Friend Request has been sent successfully.");
			return "success";
		}else{
			addActionError("Technical issue occurred.Please try again");
			return "error";
		}
	}
}
