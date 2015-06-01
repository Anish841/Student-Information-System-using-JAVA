package org.iiitb.sis.action.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.iiitb.sis.dao.CourseDAO;
import org.iiitb.sis.dao.impl.CourseDAOImpl;
import org.iiitb.sis.model.Subject;
import org.iiitb.sis.model.User;

import com.opensymphony.xwork2.ActionSupport;

public class StudentEnrolledSubjects extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Subject> subjectList = null;
	
	HttpSession session = ServletActionContext.getRequest().getSession(false);
	User user = (User)session.getAttribute("user");
	String userId = user.getUserId();
	
	
	public String getEnrolledList(){
		CourseDAO cd = new CourseDAOImpl();
		
		setSubjectList(cd.getEnrolledCourses(userId));
		System.out.println("from action "+getSubjectList().size());
		return SUCCESS;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

}
