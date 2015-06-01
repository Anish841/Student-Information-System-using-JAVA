package org.iiitb.sis.dao;

import java.util.ArrayList;

import org.iiitb.sis.model.EnrolledStudents;
import org.iiitb.sis.model.Subject;

public interface FacultyCoursesDAO 
{
	public ArrayList<Subject> populatedropdown(String facultyid);
	public ArrayList<EnrolledStudents> getEnrolledStudents(String Subject_id);
	public ArrayList<Subject> getAllFacultyCourses(String facultyid);
	public String getSubjectName(String subjectId);
}
