package org.iiitb.sis.action.admin;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.sis.dao.CourseDAO;
import org.iiitb.sis.dao.impl.CourseDAOImpl;
import org.iiitb.sis.model.Student;
import org.iiitb.sis.model.Subject;
import org.iiitb.sis.util.*;

import com.opensymphony.xwork2.ActionSupport;

public class AddCourseAction extends ActionSupport{
	
	private ArrayList<Subject> subjectList;
	private ArrayList<String> programmeList;
	private ArrayList<Student> facultyList;
	private ArrayList<String> termList;
	private String programme;
	private String term;
	private String subject;
	private List<String> faculty;
	private String credit;
	private String course_sub;
	private String course_fac;
	private String course_cre;
	private String temp_programme;
	private String temp_term;
	private ArrayList<String> course_subList;
	private ArrayList<String> course_facList;
	private ArrayList<String> course_creList;
	
	
	
	public List<String> getFaculty() {
		return faculty;
	}

	public void setFaculty(List<String> faculty) {
		this.faculty = faculty;
	}

	public String getTemp_term() {
		return temp_term;
	}

	public void setTemp_term(String temp_term) {
		this.temp_term = temp_term;
	}

	public ArrayList<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(ArrayList<Subject> subjectList)
	{
		this.subjectList=subjectList;
	}
	
	public ArrayList<String> getProgrammeList() {
		return programmeList;
	}

	public void setProgrammeList(ArrayList<String> programmeList) {
		this.programmeList = programmeList;
	}
	

	public ArrayList<String> getTermList() {
		return termList;
	}

	public void setTermList(ArrayList<String> termList) {
		this.termList = termList;
	}
	
	
	public ArrayList<String> setCourseProgrammeList() {
		CourseDAO courseDAO = new CourseDAOImpl();
		return courseDAO.getProgrammeList();		
	}
	
	public String populateCourseDropDowns()
	{
		ArrayList<String> al=setCourseProgrammeList();
		setProgrammeList(al);
		return "success";
	}
	
	public String getTermDetail() {
		setTemp_programme(programme);
		CourseDAO courseDAO = new CourseDAOImpl();
		setTermList(courseDAO.getTermList(programme));
		ArrayList<String> al=setCourseProgrammeList();
		if(programme!=null || programme!=""){
			al.remove(programme);
			setProgrammeList(al);
		}
		return "success";
	}
	
	public String getSubjectDetail(){
		setTemp_term(term);
		getTermDetail();
		String  termId=programme+"_"+term;
		CourseDAO courseDAO = new CourseDAOImpl();
		setSubjectList(courseDAO.getSubjectList(termId));
		ArrayList<String> al=courseDAO.getTermList(programme);
		if(term!=null || term!=""){
			al.remove(term);
			setTermList(al);
		}
		setFacultyList(courseDAO.getFacultyList());
		return "success";
	}
	public String execute()
	{
		CourseDAO courseDAO = new CourseDAOImpl();
		courseDAO.addCourse(subject,faculty);
		addActionMessage("Faculty has been assigned successfully");
		return "success";
	}
	
	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}


	

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public ArrayList<Student> getFacultyList() {
		return facultyList;
	}

	public void setFacultyList(ArrayList<Student> facultyList) {
		this.facultyList = facultyList;
	}



	public String getCourse_sub() {
		return course_sub;
	}



	public void setCourse_sub(String course_sub) {
		this.course_sub = course_sub;
	}



	public String getCourse_fac() {
		return course_fac;
	}



	public void setCourse_fac(String course_fac) {
		this.course_fac = course_fac;
	}



	public String getCourse_cre() {
		return course_cre;
	}



	public void setCourse_cre(String course_cre) {
		this.course_cre = course_cre;
	}



	public ArrayList<String> getCourse_subList() {
		return course_subList;
	}



	public void setCourse_subList(ArrayList<String> course_subList) {
		this.course_subList = course_subList;
	}



	public ArrayList<String> getCourse_facList() {
		return course_facList;
	}



	public void setCourse_facList(ArrayList<String> course_facList) {
		this.course_facList = course_facList;
	}



	public ArrayList<String> getCourse_creList() {
		return course_creList;
	}



	public void setCourse_creList(ArrayList<String> course_creList) {
		this.course_creList = course_creList;
	}



	


	public String getTemp_programme() {
		return temp_programme;
	}



	public void setTemp_programme(String temp_programme) {
		this.temp_programme = temp_programme;
	}
	

}
