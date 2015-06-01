package org.iiitb.sis.dao;

import java.util.List;

import org.iiitb.sis.model.Terms;
import org.iiitb.sis.model.UserGrades;

public interface UserGradesDAO
{
	public List<UserGrades> getGradesAllSemAllSub(String user_id);
	public List<Terms> getTerms(String user_id);
	public List<UserGrades> getGradesPerSemAllSub(String user_id,String term_id);
}
