package org.iiitb.sis.action.admin;

import org.iiitb.sis.dao.AddProgrammeDAO;
import org.iiitb.sis.dao.impl.AddProgrammeDAOImpl;

import com.opensymphony.xwork2.ActionSupport;

public class AddProgrammeAction extends  ActionSupport
{
	private String programmeName;
	private String noOfTerms;
	public String getProgrammeName() {
		return programmeName;
	}
	public void setProgrammeName(String programmeName) {
		this.programmeName = programmeName;
	}
	public String getNoOfTerms() {
		return noOfTerms;
	}
	public void setNoOfTerms(String noOfTerms) {
		this.noOfTerms = noOfTerms;
	}
	
	public String addProgramme()
	{
		AddProgrammeDAO acd = new AddProgrammeDAOImpl();
		boolean flag = acd.addProgramme(programmeName, noOfTerms);
		if(flag)
		{
			addActionMessage("Programme has been added successfully.");
			return SUCCESS;
		}
		else
		{
			addActionError("Programme name already exists.");
			return ERROR;
		}
	}
}
