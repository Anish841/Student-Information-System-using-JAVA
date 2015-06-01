package org.iiitb.sis.dao;

import java.util.ArrayList;

import org.iiitb.sis.model.TermSubject;

public interface TermSubjectDAO {
	public ArrayList<String> populatedropdown();
	public ArrayList<TermSubject> getTermSubject(String programme);

}
