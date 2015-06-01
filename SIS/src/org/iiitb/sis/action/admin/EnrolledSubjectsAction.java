package org.iiitb.sis.action.admin;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.sis.dao.EnrolledSubjectDAO;
import org.iiitb.sis.dao.impl.EnrolledSubjectDAOImpl;
import org.iiitb.sis.model.User;
import org.iiitb.sis.util.ConnectionPool;

import com.opensymphony.xwork2.ActionSupport;

public class EnrolledSubjectsAction extends ActionSupport{
	HttpSession session=ServletActionContext.getRequest().getSession(false);
	User u=(User)session.getAttribute("user");
	String id = u.getUserId();
	ArrayList<String> subject;
	
	public ArrayList<String> getSubject() {
		return subject;
	}
	public void setSubject(ArrayList<String> subject) {
		this.subject = subject;
	}

	public String execute() throws Exception {
		
		EnrolledSubjectDAO ad = new EnrolledSubjectDAOImpl();
		//Connection connection = ConnectionPool.getConnection();
		//System.out.println("connection in enrolled subjects action class"+connection);
		String result=ad.enrolledsubjects(id,subject);
		//ConnectionPool.freeConnection(connection);
		//System.out.println("done!!");
		if(result.equals("success"))
		{
		addActionMessage("Enrollment Succesful!");
		return "success";
		}
		else
		{
		addActionError("Technical issue occurred ,please try again later.");
		return "error";
		}
		
		

	
	}

}
