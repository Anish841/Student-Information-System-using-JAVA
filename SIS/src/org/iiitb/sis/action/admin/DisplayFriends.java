package org.iiitb.sis.action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.iiitb.sis.dao.FriendsDAO;
import org.iiitb.sis.dao.impl.FriendsDAOImpl;
import org.iiitb.sis.dao.impl.NotificationDAOImpl;
import org.iiitb.sis.model.User;

import com.opensymphony.xwork2.ActionSupport;


public class DisplayFriends extends ActionSupport{
	
	ArrayList<User> userData = new ArrayList<User>();
	String LastLogin = "";
	String friendid; 
	User user;
	String checkFriend;
	private String uid1;
	private String uid2;
	private String message;
	private String checkNoFriend;
	
	public String getCheckNoFriend() {
		return checkNoFriend;
	}


	public void setCheckNoFriend(String checkNoFriend) {
		this.checkNoFriend = checkNoFriend;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getUid1() {
		return uid1;
	}


	public void setUid1(String uid1) {
		this.uid1 = uid1;
	}


	public String getUid2() {
		return uid2;
	}


	public void setUid2(String uid2) {
		this.uid2 = uid2;
	}


	public String getCheckFriend() {
		return checkFriend;
	}


	public void setCheckFriend(String checkFriend) {
		this.checkFriend = checkFriend;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public ArrayList<User> getUserData() {
		return userData;
	}


	public void setUserData(ArrayList<User> userData) {
		this.userData = userData;
	}


	public String getLastLogin() {
		return LastLogin;
	}


	public void setLastLogin(String lastLogin) {
		LastLogin = lastLogin;
	}

	
	public String populateFriends(){
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		User u=(User)session.getAttribute("user");
		HttpServletRequest request=ServletActionContext.getRequest();
		System.out.println("ans :"+u.getUserId());
		FriendsDAO fetchfriends = new FriendsDAOImpl();
		ArrayList<User> al=fetchfriends.getAllFriends(u.getUserId());
		setUserData(al);
		request.setAttribute("userData", al);
		setLastLogin(fetchfriends.getLastLogin(u.getUserId()));
		return "success";
	}

	public String getFriendid() {
		return friendid;
	}


	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}

	
	public String execute(){
		
		if(friendid != null){
			FriendsDAO displayfriendsprofile = new FriendsDAOImpl();
			User u=displayfriendsprofile.getSingleUser(friendid);
			if(u.isFriend()){
				checkFriend="true";
			}else{
				checkFriend="false";
			}
			System.out.println("isnide exueue==>"+u.isFriend());
			setUser(u);
			System.out.println("inside friendid==>"+friendid);
		}		
		return "success";
	}
	public String sendMessage(){
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		User u=(User)session.getAttribute("user");
		System.out.println("Messaege===>"+message);
		System.out.println("frienddid==>"+friendid);
		execute();
		String msg=u.getName()+":"+message;
		String url="searchAction?friendid="+u.getUserId();
		NotificationDAOImpl n=new NotificationDAOImpl();
		n.notifyUser(friendid, msg, "MSG", url);
		addActionMessage("Message has been sent successfully");
		return "success";
	}
	public String addFriend(){
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		User u=(User)session.getAttribute("user");
		FriendsDAO fd=new FriendsDAOImpl();
		boolean f=fd.addFriend(uid1, uid2);
		friendid=uid2;
		execute();
		if(f){
			String msg=u.getName()+" has added you as a friend";
			String url="searchAction?friendid="+uid1;
			NotificationDAOImpl n=new NotificationDAOImpl();
			n.notifyUser(uid2, msg, "FRND_REQ", url);
			addActionMessage("Friend has been added successfully");
			return "success";
		}else{
			addActionError("Technical issue occurred.Please try again");
			return "error";
		}
	}
	
	public String removeFriend(){
		System.out.println(uid1+" "+uid2);
		System.out.println("remove friend");
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		User u=(User)session.getAttribute("user");
		
		FriendsDAO fd=new FriendsDAOImpl();
		System.out.println("remove friend1");
		boolean f=fd.removeFriend(uid1, uid2);
		System.out.println("remove friend12");
		friendid=uid2;
		execute();
		if(f){
			String msg=u.getName()+" has removed you from friend-list.";
			String url="searchAction?friendid="+uid1;
			NotificationDAOImpl n=new NotificationDAOImpl();
			n.notifyUser(uid2, msg, "FRND_REQ", url);
			User utemp=getUser();
			addActionMessage(utemp.getName()+" is no longer Your Friend");
			return "success";
		}else{
			addActionError("Technical issue occurred.Please try again");
			return "error";
		}
	}
   
	
   public String getFriend()
   {
	   HttpSession session=ServletActionContext.getRequest().getSession(false);
		User u=(User)session.getAttribute("user");
	   if(friendid != null){
		   
		   if(u.getUserId().equalsIgnoreCase(friendid)){
			   friendid=null;
			   return "success";
		   }
			FriendsDAO displayfriendsprofile = new FriendsDAOImpl();
			User u1=displayfriendsprofile.SearchUser(friendid,u.getUserId());
			if(u1==null){
				checkNoFriend="Yes";
				return "error";
			}
			setUser(u1);
			if(u1.isFriend()){
				checkFriend="true";
			}else{
				checkFriend="false";
			}
			//System.out.println("getFriend==>"+u1.isFriend());
		}		
	   if(getUser()==null)
	   {
		   return "error";
	   }
	   return "success";
   }
}
