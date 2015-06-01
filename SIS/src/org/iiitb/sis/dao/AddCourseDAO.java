package org.iiitb.sis.dao;

import java.util.List;

import org.iiitb.sis.model.Terms;

public interface AddCourseDAO
{
	public List<String> getProgramme();
	public List<Terms> getTerms(String programmeName);
	public String addCourse(String termID, String subjectID, String subjectName, String credits, String programmeName);
}
