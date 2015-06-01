package org.iiitb.sis.dao;

import java.util.ArrayList;

import org.iiitb.sis.model.Subject;
import org.iiitb.sis.model.User;


public interface AdminSubjectfromFacultyDAO 
{
	public ArrayList<User> populatedropdown();
	public ArrayList<Subject> getSubjectInfo(String Faculty_id);
	 

}
