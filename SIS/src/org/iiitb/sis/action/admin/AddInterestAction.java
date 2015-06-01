package org.iiitb.sis.action.admin;

import org.iiitb.sis.dao.AddProgrammeDAO;
import org.iiitb.sis.dao.impl.AddInterestDAOImpl;
import org.iiitb.sis.dao.impl.AddProgrammeDAOImpl;

import com.opensymphony.xwork2.ActionSupport;

public class AddInterestAction extends  ActionSupport{

	private String interestName;
	private String intDetails;
	public String getInterestName() {
		return interestName;
	}
	public void setInterestName(String interestName) {
		this.interestName = interestName;
	}
	public String getIntDetails() {
		return intDetails;
	}
	public void setIntDetails(String intDetails) {
		this.intDetails = intDetails;
	}
	
	public String addInterest()
	{
		AddInterestDAOImpl acd=new AddInterestDAOImpl();
		boolean flag = acd.addInterest(interestName, intDetails);
		if(flag)
		{
			addActionMessage("Interest has been added successfully.");
			return SUCCESS;
		}
		else
		{
			addActionError("Interest already exists.");
			return ERROR;
		}
	}
}
