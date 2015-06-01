package org.iiitb.sis.action.admin;

import org.apache.struts2.ServletActionContext;
import org.iiitb.sis.util.ConnectionPool;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.iiitb.sis.dao.NewsDAO;
import org.iiitb.sis.dao.GradesDAO;
import org.iiitb.sis.dao.impl.NewsDAOImpl;
import org.iiitb.sis.dao.impl.GradesDAOImpl;
import org.iiitb.sis.model.News;
import org.iiitb.sis.model.Student;
import org.iiitb.sis.model.Course;
import org.iiitb.sis.model.Grade;
import org.iiitb.sis.model.User;

import com.opensymphony.xwork2.ActionSupport;


public class AssignGradesAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> studentList;
	private List<String> courseList;
	private List<String> gradeList;
	private String studentDisplayChoice;
	private String newchoice;
	private String msg;
	private String checkStudent="false";
	
	public String getCheckStudent() {
		return checkStudent;
	}


	public void setCheckStudent(String checkStudent) {
		this.checkStudent = checkStudent;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getNewchoice() {
		return newchoice;
	}


	public void setNewchoice(String newchoice) {
		this.newchoice = newchoice;
	}


	public List<String> getStudentList() {
		return studentList;
	}


	public void setStudentList(List<String> studentList) {
		this.studentList = studentList;
	}


	public List<String> getCourseList() {
		return courseList;
	}


	public void setCourseList(List<String> courseList) {
		this.courseList = courseList;
	}


	public List<String> getGradeList() {
		return gradeList;
	}


	public void setGradeList(List<String> gradeList) {
		this.gradeList = gradeList;
	}


	public String getStudentDisplayChoice() {
		return studentDisplayChoice;
	}


	public void setStudentDisplayChoice(String studentDisplayChoice) {
		this.studentDisplayChoice = studentDisplayChoice;
	}


	public String getCourseDisplayChoice() {
		return courseDisplayChoice;
	}


	public void setCourseDisplayChoice(String courseDisplayChoice) {
		this.courseDisplayChoice = courseDisplayChoice;
	}


	public String getGradeDisplayChoice() {
		return gradeDisplayChoice;
	}


	public void setGradeDisplayChoice(String gradeDisplayChoice) {
		this.gradeDisplayChoice = gradeDisplayChoice;
	}


	private String courseDisplayChoice;
	private String gradeDisplayChoice;
	GradesDAO item =  new GradesDAOImpl();
	
	public AssignGradesAction()
	{
		studentList = new LinkedList<String>();
		//studentList.add(DEFAULT_TERM);

		courseList = new LinkedList<String>();
		//courseList.add(DEFAULT_COURSE);

		//setResultList(new LinkedList<GradeInfo>());
		gradeList = new LinkedList<String>();
	}
	
	
	public String filldetails() throws Exception
	{
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		User u=(User)session.getAttribute("user");
		 studentList.addAll(new GradesDAOImpl().getStudent(u.getUserId()));
		 System.out.println(studentDisplayChoice);
		 System.out.println("successful in fetching Students");
		 if (null == studentDisplayChoice) {
			 if( studentList!=null && studentList.size()>0){
				 checkStudent="false"; 
				 studentDisplayChoice = studentList.get(0);
			 }else{
				 checkStudent="true";
			 }
			}else{
				System.out.println("----------->"+checkStudent);
				checkStudent="false";
			}
		 gradeList.addAll(new GradesDAOImpl().getGrade());
		 System.out.println("successful in fetching Grades");
		 
		 courseList.addAll(new GradesDAOImpl().getCourse(studentDisplayChoice,u.getUserId()));
		 System.out.println("successful in fetching Course");
		 
		
		return "success";
		
	}
	public String assignGrade()
	{
		boolean k=false;
		k=item.addGrade(studentDisplayChoice, courseDisplayChoice, gradeDisplayChoice);
		if(k==true)
		{
			setMsg("true");
			System.out.println("Grades Assigned Succesfully!");
			addActionMessage("Grades have been assigned successfully.");
			return "success";
		}
		else
		{
			System.out.println("Failed to assign grades");
			return "error";
		}
		
	}
	public String isAssigned()
	{
		boolean k=false;
		
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		User u=(User)session.getAttribute("user");
		 studentList.addAll(new GradesDAOImpl().getStudent(u.getUserId()));
		 System.out.println(studentDisplayChoice);
		 System.out.println("successful in fetching Students");
		 if (null == studentDisplayChoice) {
			 if( studentList!=null && studentList.size()>0){
				 checkStudent="false"; 
				 studentDisplayChoice = studentList.get(0);
			 }else{
				 checkStudent="true";
			 }
			}else{
				System.out.println("----------->"+checkStudent);
				checkStudent="false";
			}
		 gradeList.addAll(new GradesDAOImpl().getGrade());
		 System.out.println("successful in fetching Grades");
		 
		 courseList.addAll(new GradesDAOImpl().getCourse(studentDisplayChoice,u.getUserId()));
		 System.out.println("successful in fetching Course");
		
		String str2=null;
		str2=item.viewAssignmentHistory(studentDisplayChoice,courseDisplayChoice);
		System.out.println("Already assigned??grade="+str2);
		if(str2!=null)
		{
			setMsg("true");
			//System.out.println("Grades Assigned Succesfully!");
			addActionError("You have already assigned a grade "+str2+" to this student for the selected course. You can edit the grade from the dropdown below");
			return "success";
		}
		else
		{
			System.out.println("No grades assigned for this student");
			
			return "error";
		}
		
	}
	

}
