package org.iiitb.sis.action.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.iiitb.sis.dao.AnnouncementsDAO;
import org.iiitb.sis.dao.UserGradesDAO;
import org.iiitb.sis.dao.impl.AnnouncementsDAOImpl;
import org.iiitb.sis.dao.impl.UserGradesDAOImpl;
import org.iiitb.sis.model.Announcements;
import org.iiitb.sis.model.Terms;
import org.iiitb.sis.model.User;
import org.iiitb.sis.model.UserGrades;

import com.opensymphony.xwork2.ActionSupport;

public class UserGradesAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	UserGradesDAO gdao =  new UserGradesDAOImpl();
	private List<UserGrades> gradesList;
	private List<Terms> termList;
	private String term_id;
	
	public String fetchGradesAllSemAllSub()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		User u=(User)session.getAttribute("user");
		termList = gdao.getTerms(u.getUserId());
		request.setAttribute("termList", termList);
		request.setAttribute("term_id", "All Semester");
		gradesList = gdao.getGradesAllSemAllSub(u.getUserId());
		setGradesList(gradesList);
		return SUCCESS;
	}
	public String fetchGradesPerSemAllSub()
	{
		System.out.println("fetchGradesPerSemAllSub()");
		if(getTerm_id().equals("All Semesters"))
		{
			fetchGradesAllSemAllSub();
		}
		else
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session=ServletActionContext.getRequest().getSession(false);
			User u=(User)session.getAttribute("user");
			termList = gdao.getTerms(u.getUserId());
			request.setAttribute("termList", termList);
			request.setAttribute("term_id", getTerm_id());
			System.out.println("termList:"+termList.size());
			gradesList = gdao.getGradesPerSemAllSub(u.getUserId(),getTerm_id());
			setGradesList(gradesList);
		}
		return SUCCESS;
	}
	
	public UserGradesDAO getGdao() {
		return gdao;
	}

	public void setGdao(UserGradesDAO gdao) {
		this.gdao = gdao;
	}

	public List<UserGrades> getGradesList() {
		return gradesList;
	}

	public void setGradesList(List<UserGrades> gradesList) {
		this.gradesList = gradesList;
	}
	public List<Terms> getTermList() {
		return termList;
	}
	public void setTermList(List<Terms> termList) {
		this.termList = termList;
	}
	public String getTerm_id() {
		return term_id;
	}
	public void setTerm_id(String term_id) {
		this.term_id = term_id;
	}	
}
