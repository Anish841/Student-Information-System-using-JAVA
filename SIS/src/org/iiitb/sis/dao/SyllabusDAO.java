package org.iiitb.sis.dao;

import java.io.File;
import java.util.ArrayList;

import org.iiitb.sis.model.Subject;


public interface SyllabusDAO {
	
	public boolean setSyllabus(String courseName, String syllabus,int type);

	public ArrayList<Subject> getAllSubjects(String facultyId);
	public Subject populateSyllabus(String subject);

	public ArrayList<Subject> getAllSyllabus();
	
}
