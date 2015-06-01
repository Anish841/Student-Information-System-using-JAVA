package org.iiitb.sis.dao;

import java.util.ArrayList;

import org.iiitb.sis.model.User;
import org.iiitb.sis.model.Subject;

public interface AdminFacultyFromSubjectsDAO {

	public ArrayList<Subject> getAllSubjects();

	public ArrayList<User> getAllFaculties(String subject_id);
	
	

}
