package org.iiitb.sis.action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.iiitb.sis.dao.TermSubjectDAO;
import org.iiitb.sis.dao.impl.GetStudentInterestDAO;
import org.iiitb.sis.dao.impl.TermSubjectDAOImpl;
import org.iiitb.sis.model.Interest;
import org.iiitb.sis.model.Student;
import org.iiitb.sis.model.User;

import com.opensymphony.xwork2.ActionSupport;

public class GetStudentInterestAction extends ActionSupport{
	
	ArrayList<Interest> interestList;
	String message;
	ArrayList<Student> studentList;
	String interestId;
	String interestName;
	
	
	public String getInterestName() {
		return interestName;
	}

	public void setInterestName(String interestName) {
		this.interestName = interestName;
	}

	public String getInterestId() {
		return interestId;
	}

	public void setInterestId(String interestId) {
		this.interestId = interestId;
	}

	public ArrayList<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public ArrayList<Interest> getInterestList() {
		return interestList;
	}

	public void setInterestList(ArrayList<Interest> interestList) {
		this.interestList = interestList;
	}

	public String populateInterest()
	{
		GetStudentInterestDAO gsid=new GetStudentInterestDAO();
		gsid.populatedropdown();
		interestList = gsid.populatedropdown();
		setInterestList(interestList);
		setMessage("");
		return "Success";
	}
	
	
	public String getStudents()
	{
		GetStudentInterestDAO gsid=new GetStudentInterestDAO();
		studentList=gsid.getStudents(interestId);
		populateInterest();
		interestName=gsid.getIntname(interestId);
		setMessage(interestName);
		for(int i=0;interestList!=null && i<interestList.size();i++){
			Interest ih=interestList.get(i);
			if(interestName.equalsIgnoreCase(ih.getInterestName())){
				interestList.remove(i);
			}
		}
		return "Success";
	}
}
