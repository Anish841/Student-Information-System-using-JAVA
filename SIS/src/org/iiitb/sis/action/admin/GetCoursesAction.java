package org.iiitb.sis.action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.iiitb.sis.dao.FacultyCoursesDAO;
import org.iiitb.sis.dao.impl.FacultyCoursesDAOImpl;
import org.iiitb.sis.model.EnrolledStudents;
import org.iiitb.sis.model.Subject;
import org.iiitb.sis.model.User;

public class GetCoursesAction 
{
	ArrayList<Subject> course;
	ArrayList<Subject> fcourse;
	String Subject_id;
	
	String message;
	ArrayList<EnrolledStudents> enrolledStudents=new ArrayList<EnrolledStudents>();
	
	public ArrayList<Subject> getFcourse() {
		return fcourse;
	}
	public void setFcourse(ArrayList<Subject> fcourse) {
		this.fcourse = fcourse;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubject_id() {
		return Subject_id;
	}
	public void setSubject_id(String subject_id) {
		Subject_id = subject_id;
	}
	public ArrayList<EnrolledStudents> getEnrolledStudents() {
		return enrolledStudents;
	}
	public void setEnrolledStudents(ArrayList<EnrolledStudents> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}
	
	public ArrayList<Subject> getCourse() {
		return course;
	}
	public void setCourse(ArrayList<Subject> course) {
		this.course = course;
	}
	public String populateCourseFaculty()
	{
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		User u=(User)session.getAttribute("user");
		FacultyCoursesDAO sd = new FacultyCoursesDAOImpl();
		course = sd.populatedropdown(u.getUserId());
		setCourse(course);
		setMessage("");
		return "Success";
	}
	public String getAllFacultyCourse()
	{
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		User u=(User)session.getAttribute("user");
		FacultyCoursesDAO sd = new FacultyCoursesDAOImpl();
		fcourse = sd.getAllFacultyCourses(u.getUserId());
		setFcourse(fcourse);
		setMessage("");
		return "Success";
	}
	
	public String getenrolledStudent()
	{
		FacultyCoursesDAO sd = new FacultyCoursesDAOImpl();
		enrolledStudents=sd.getEnrolledStudents(Subject_id);
		populateCourseFaculty();
		String subjectName=sd.getSubjectName(Subject_id);
		setMessage(Subject_id+" - "+subjectName);
		int i=0;
		while(course!=null && course.size()>0){
			Subject s=course.get(i++);
			if(Subject_id.equalsIgnoreCase(s.getSubjectCode())){
				course.remove(i-1);break;
			}
		}
		return "Success";
	}
}
