package org.iiitb.sis.action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import org.iiitb.sis.dao.AdminSubjectfromFacultyDAO;
import org.iiitb.sis.dao.impl.AdminSubjectfromFacultyDAOImpl;

import org.iiitb.sis.model.Subject;
import org.iiitb.sis.model.User;


public class GetAssSubToFacultyAction 
{
	ArrayList<User>user;
	ArrayList<Subject>subjectInfo;
	String Faculty_id;
	String defaultValue;
	
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	String msg;
	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getFaculty_id() {
		return Faculty_id;
	}

	public void setFaculty_id(String faculty_id) {
		Faculty_id = faculty_id;
	}

	public ArrayList<Subject> getSubjectInfo() {
		return subjectInfo;
	}

	public void setSubjectInfo(ArrayList<Subject> subjectInfo) {
		this.subjectInfo = subjectInfo;
	}

	public ArrayList<User> getUser() {
		return user;
	}

	public void setUser(ArrayList<User> user) {
		this.user = user;
	}

	public String populateFacultyId()
	{
		/*HttpSession session=ServletActionContext.getRequest().getSession(false);
		User u=(User)session.getAttribute("user");
		FacultyCoursesDAO sd = new FacultyCoursesDAOImpl();
		
	*/	
		AdminSubjectfromFacultyDAO sd = new AdminSubjectfromFacultyDAOImpl();
		user = sd.populatedropdown();
		//setFaculty_name(faculty_name);
		setUser(user);
		//System.out.println(user.get(0).getUserId());
		setMsg("");
		//setMessage("");
		return "success";
	}
	public String GetAssignedSubject()
	{
		AdminSubjectfromFacultyDAO sd = new AdminSubjectfromFacultyDAOImpl();
		setSubjectInfo(sd.getSubjectInfo(Faculty_id));
		user = sd.populatedropdown();
		if(subjectInfo.size()==0|| subjectInfo==null)
		{
			setMsg("No subject alloted to the faculty!!");
			
		}
		
		else
		{
			setMsg("");
			
		}
		for(int i=0;i<user.size();i++)
		{
			if(Faculty_id.equals(user.get(i).getUserId()))
			{
				
				setDefaultValue(user.get(i).getName());
				user.remove(i);
				break;
			} 
			 
		}
		
		/*System.out.println(getSubjectInfo().get(0).getSubjectCode());
		System.out.println(getSubjectInfo().get(0).getSubjectName());
		System.out.println(Faculty_id);*/
		
		return "success";	
	}

}
