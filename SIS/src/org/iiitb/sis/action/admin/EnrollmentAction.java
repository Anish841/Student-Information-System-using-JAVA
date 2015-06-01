package org.iiitb.sis.action.admin;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.iiitb.sis.dao.EnrollmentDAO;
import org.iiitb.sis.dao.impl.EnrollmentDAOImpl;
import org.iiitb.sis.model.User;
import org.iiitb.sis.util.ConnectionPool;

public class EnrollmentAction {
	HttpSession session=ServletActionContext.getRequest().getSession(false);
	User u=(User)session.getAttribute("user");
	String id = u.getUserId();
    
	private ArrayList<String> temp_subject= new ArrayList<String>();
	private ArrayList<String> add_subject= new ArrayList<String>();
	
	public String execute() throws NamingException, SQLException,
	FileNotFoundException {
		System.out.println("Userid---------->>"+id);
	EnrollmentDAO ad = new EnrollmentDAOImpl();
	Connection connection = ConnectionPool.getConnection();
	System.out.println("Connection in enrollment action class:" + connection);

	//ad.enroll(connection,temp_subject);
	setTemp_subject(ad.enroll(id,connection,temp_subject));
	ConnectionPool.freeConnection(connection);
	

return "success";
	}

	
		public ArrayList<String> getTemp_subject() {
		return temp_subject;
	}

	public void setTemp_subject(ArrayList<String> temp_subject) {
		this.temp_subject = temp_subject;
	}


	public ArrayList<String> getAdd_subject() {
		return add_subject;
	}


	public void setAdd_subject(ArrayList<String> add_subject) {
		this.add_subject = add_subject;
	}
	
	

}
