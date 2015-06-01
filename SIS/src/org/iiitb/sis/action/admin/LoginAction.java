package org.iiitb.sis.action.admin;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.sis.dao.LoginDAO;
import org.iiitb.sis.dao.NotificationDAO;
import org.iiitb.sis.dao.impl.LoginDAOimpl;
import org.iiitb.sis.dao.impl.NotificationDAOImpl;
import org.iiitb.sis.model.Notification;
import org.iiitb.sis.model.User;
import org.iiitb.sis.util.ConnectionPool;
import org.iiitb.sis.util.Constant;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{
	private String userID;
	private String password;
	private SessionMap<String, Object> session;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, Object> getSession()
	{
		return session;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=(SessionMap)session;
	}
	
	public String execute() throws NamingException, SQLException
	{
		System.out.println(":::::::::::::::::"+session);
		User user = (User) session.get("user");
		if (user != null)
		{
			if(user.getUserType().equalsIgnoreCase("A"))
				return "admin";
			else
				return "user";
		}
		else
		{
			User newUser = new User(userID, password);
			LoginDAO ld=new LoginDAOimpl();
			User usr=ld.isValidUser(newUser);
			if (!usr.isErrorFlag())
			{
				session.put("user", usr);
				session.put("login", true);
				if (newUser.getUserType().equalsIgnoreCase("A"))
				{
					return "admin";
				}else if(newUser.getUserType().equalsIgnoreCase("F")){
					return "faculty";
				}
				else{
					NotificationDAO nd=new NotificationDAOImpl();
					ArrayList<Notification> al=nd.getAllNotification(userID);
					System.out.println("getting values from userID==>"+userID);
					session.put("notiList", al);
					return "user";
				}
			}
			else
			{
				System.out.println("invalid user");
				addActionError(usr.getErrorMessage());
				return ERROR;
			}

		}

	}
	
	public String logout(){  
	    if(session!=null && session.get("login")!=null){
	    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    	Date dateobj = new Date();
	    	LoginDAO ld=new LoginDAOimpl();
	    	User u=(User)session.get("user");
	    	ld.updateLogoutTime(u.getUserId(), df.format(dateobj).toString());
	    	session.put("login", null);
	    	session.remove("user");
	        session.invalidate();  
	        addActionMessage("Your Account has been successfully logged out");
	    }  
	    return "success";  
	}  
}
