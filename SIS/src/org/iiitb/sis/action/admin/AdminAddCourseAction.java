package org.iiitb.sis.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.iiitb.sis.dao.AddCourseDAO;
import org.iiitb.sis.dao.impl.AddCourseDAOImpl;
import org.iiitb.sis.model.Terms;

import com.opensymphony.xwork2.ActionSupport;

public class AdminAddCourseAction extends  ActionSupport
{
	private String subjectID;
	private String subjectName;
	private String credits;
	private String programmeName;
	private String termID;
	private List<Terms> termsList;
	private List<String> programmeList;
	
	
	
	public List<Terms> getTermsList() {
		return termsList;
	}
	public void setTermsList(List<Terms> termsList) {
		this.termsList = termsList;
	}
	public List<String> getProgrammeList() {
		return programmeList;
	}
	public void setProgrammeList(List<String> programmeList) {
		this.programmeList = programmeList;
	}
	public String getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}
	public String getProgrammeName() {
		return programmeName;
	}
	public void setProgrammeName(String programmeName) {
		this.programmeName = programmeName;
	}
	public String getTermID() {
		return termID;
	}
	public void setTermID(String termID) {
		this.termID = termID;
	}
	public String getProgramme()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("programmeList", programmeList);
		AddCourseDAO acd = new AddCourseDAOImpl();
		programmeList = acd.getProgramme();
		setProgrammeList(programmeList);
		return SUCCESS;
	}
	public String getTerms()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		AddCourseDAO acd = new AddCourseDAOImpl();
		termsList = acd.getTerms(programmeName);
		programmeList = acd.getProgramme();
		request.setAttribute("programmeList", programmeList);
		request.setAttribute("termsList", termsList);
		request.setAttribute("subjectID", subjectID);
		request.setAttribute("subjectName", subjectName);
		request.setAttribute("programmeName", programmeName);
		request.setAttribute("credits", credits);
		return SUCCESS;
	}
	public String addCourse()
	{
		AddCourseDAO acd = new AddCourseDAOImpl();
		HttpServletRequest request = ServletActionContext.getRequest();
		String msg = acd.addCourse(termID, subjectID, subjectName, credits, programmeName);
		termsList = acd.getTerms(programmeName);
		programmeList = acd.getProgramme();
		request.setAttribute("programmeList", programmeList);
		request.setAttribute("termsList", termsList);
		request.setAttribute("subjectID", subjectID);
		request.setAttribute("subjectName", subjectName);
		request.setAttribute("programmeName", programmeName);
		request.setAttribute("credits", credits);
		request.setAttribute("termID", termID);
		if(msg.equalsIgnoreCase("Course successfully added"))
		{
			addActionMessage(msg);
			return SUCCESS;
		}
		else
		{
			addActionError(msg);
			return ERROR;
		}
	}
}
