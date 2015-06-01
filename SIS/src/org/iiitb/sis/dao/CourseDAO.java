package org.iiitb.sis.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.sis.model.Student;
import org.iiitb.sis.model.Subject;

public interface CourseDAO{
	
	public ArrayList<Subject> getSubjectList(String termId);
	public ArrayList<String> getProgrammeList();
	public ArrayList<String> getTermList(String prog);
	public ArrayList<Student> getFacultyList();
	public boolean addCourse(String subjectId,List<String> facultyIdList);
	public ArrayList<Subject> getAllCourses(Connection connection, String string);
	public ArrayList<Subject> getEnrolledCourses(Connection connection,
			String string);
	public String getSyllabus(Connection connection,
			String subjectCode);
	public ArrayList<Subject> getEnrolledCourses(String userId);
	
	

	



}
