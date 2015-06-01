package org.iiitb.sis.action.admin;

import java.util.ArrayList;

import org.iiitb.sis.dao.AdminFacultyFromSubjectsDAO;
import org.iiitb.sis.dao.impl.AdminFacultyFromSubjectsDAOImpl;
import org.iiitb.sis.model.User;
import org.iiitb.sis.model.Subject;

public class FacultiesInfoFromSubjectsAction {
	
	public ArrayList<Subject> subjectLists;
	public String Subject_id;
	public ArrayList<User> FacultyLists;
	public String defaultvalue="";
	public String errormsg="";
	
	public ArrayList<User> getFacultyLists() {
		return FacultyLists;
	}

	public void setFacultyLists(ArrayList<User> facultyLists) {
		FacultyLists = facultyLists;
	}

	public ArrayList<Subject> getSubjectLists() {
		return subjectLists;
	}

	public void setSubjectLists(ArrayList<Subject> subjectLists) {
		this.subjectLists = subjectLists;
	}

	public String getSubject_id() {
		return Subject_id;
	}

	public void setSubject_id(String subject_id) {
		Subject_id = subject_id;
	}
	
	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	
	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
	public String populateSubjectsDropDowns(){
		
		AdminFacultyFromSubjectsDAO subjects = new AdminFacultyFromSubjectsDAOImpl();
		setSubjectLists(subjects.getAllSubjects());
		return "success";
	}
	
	public String execute(){
	//	System.out.println("------------>>>"+Subject_id);
		populateSubjectsDropDowns();
		AdminFacultyFromSubjectsDAO listFaculties = new AdminFacultyFromSubjectsDAOImpl();
		setFacultyLists(listFaculties.getAllFaculties(getSubject_id()));
		
		if(FacultyLists.size() != 0 && FacultyLists != null){
			setErrormsg("");
		}
		else{
			setErrormsg("This Subject is not yet assigned to any faculty.");
		}
		for(int i=0;i<subjectLists.size();i++){
			
			if(Subject_id.equals(subjectLists.get(i).getSubjectCode())){
				setDefaultvalue(subjectLists.get(i).getSubjectCode()+" - "+subjectLists.get(i).getSubjectName());
				subjectLists.remove(i);
				break;
			}
		}
		return "success";
	}

	

	

	

}
