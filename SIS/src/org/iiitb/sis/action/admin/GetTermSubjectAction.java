package org.iiitb.sis.action.admin;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;



import org.iiitb.sis.dao.TermSubjectDAO;
import org.iiitb.sis.dao.impl.TermSubjectDAOImpl;
import org.iiitb.sis.model.TermSubject;
import org.iiitb.sis.model.User;

public class GetTermSubjectAction {

	ArrayList<String> programme;
	String term_id;
	String term_name;
	String message;
	String prog;
	ArrayList<TermSubject> termsubject=new ArrayList<TermSubject>();
	
	public String getProg() {
		return prog;
	}
	public void setProg(String prog) {
		this.prog = prog;
	}
	
	
	public ArrayList<String> getProgramme() {
		return programme;
	}
	public void setProgramme(ArrayList<String> programme) {
		this.programme = programme;
	}
	public String getTerm_id() {
		return term_id;
	}
	public void setTerm_id(String term_id) {
		this.term_id = term_id;
	}
	public String getTerm_name() {
		return term_name;
	}
	public void setTerm_name(String term_name) {
		this.term_name = term_name;
	}
	public ArrayList<TermSubject> getTermsubject() {
		return termsubject;
	}
	public void setTermsubject(ArrayList<TermSubject> termsubject) {
		this.termsubject = termsubject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String populateProgramme()
	{
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		User u=(User)session.getAttribute("user");
		TermSubjectDAO sd = new TermSubjectDAOImpl();
		programme = sd.populatedropdown();
		setProgramme(programme);
		System.out.println(programme.get(0));
		setMessage("");
		return "Success";
	}
	public String getTermSubject()
	{
		TermSubjectDAO sd = new TermSubjectDAOImpl();
		termsubject=sd.getTermSubject(prog);
		populateProgramme();
		setMessage(prog);
		programme.remove(prog);
		return "Success";
	}
	
}
