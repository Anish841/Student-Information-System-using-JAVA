package org.iiitb.sis.dao;
import java.util.List;

import org.iiitb.sis.model.Course;
import org.iiitb.sis.model.Grade;
import org.iiitb.sis.model.Student;

public interface GradesDAO {
	public List<String> getStudent(String userId);
	public List<String> getGrade();
	public List<String> getCourse(String roll,String facultyID);
	public boolean addGrade(String roll,String course,String grade);
	public String viewAssignmentHistory(String roll,String course);


}
