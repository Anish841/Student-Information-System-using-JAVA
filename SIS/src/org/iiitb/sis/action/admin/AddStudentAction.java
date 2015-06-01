package org.iiitb.sis.action.admin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.iiitb.sis.dao.AddCourseDAO;
import org.iiitb.sis.dao.AddStudentDAO;
import org.iiitb.sis.dao.impl.AddCourseDAOImpl;
import org.iiitb.sis.dao.impl.AddStudentDAOImpl;
import org.iiitb.sis.model.Terms;
import org.iiitb.sis.util.ConnectionPool;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import java.io.IOException;

public class AddStudentAction extends ActionSupport implements ServletRequestAware {

	private String id;
	private String name;
	private String pwd;
	private String email;
	private String mob;
	private String add;
	private String dob;
	private String gender;
	private String destPath;
	private String usertype;
	private String termID;
	private HttpServletRequest servletRequest;
	private List<Terms> termsList;
	private List<String> programmeList;
	private String programmeName;
	
	
	public String getTermID() {
		return termID;
	}

	public void setTermID(String termID) {
		this.termID = termID;
	}

	public String getProgrammeName() {
		return programmeName;
	}

	public void setProgrammeName(String programmeName) {
		this.programmeName = programmeName;
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String execute() throws NamingException, SQLException{
			AddStudentDAO ad = new AddStudentDAOImpl();
			Connection connection = ConnectionPool.getConnection();
			System.out.println("Connection in action class:" + connection);
//			String termid=programmeName+"_"+termID;
			ad.addstudent(connection, id, name, pwd, email, mob, add, dob,
					gender,usertype,termID);
			ConnectionPool.freeConnection(connection);
		addActionMessage("User has been created Successfully");
		return "success";
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
		request.setAttribute("userT",usertype);
		request.setAttribute("programmeList", programmeList);
		request.setAttribute("termsList", termsList);
		request.setAttribute("programmeName", programmeName);
		return SUCCESS;
	}
	
	public String getDestPath() {
		return destPath;
	}

	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
}
