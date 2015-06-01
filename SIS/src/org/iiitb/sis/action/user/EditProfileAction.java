package org.iiitb.sis.action.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.iiitb.sis.dao.EditProfileDAO;
import org.iiitb.sis.dao.LoginDAO;
import org.iiitb.sis.dao.impl.EditProfileDAOImpl;
import org.iiitb.sis.dao.impl.LoginDAOimpl;
import org.iiitb.sis.model.Interest;
import org.iiitb.sis.model.User;
import org.iiitb.sis.util.Constant;

import com.opensymphony.xwork2.ActionSupport;

public class EditProfileAction extends  ActionSupport{

	private String userId;
	private String email;
	private String password;
	private List<String> interest;
	HttpServletRequest request;
	private String mobno;
	private File pic;
	private String picContentType;
	private String picFileName;

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public List<String> getInterest() {
		return interest;
	}

	public void setInterest(List<String> interest) {
		this.interest = interest;
	}

	public String displayEditPhoto() throws FileNotFoundException{
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		User u=(User)session.getAttribute("user");
		System.out.println("displayEdited Photo===>"+picFileName);
		System.out.println("displayEdited Photo===>"+picContentType);
		System.out.println("displayEdited Photo===>"+pic);
		displayAllInterest();
		String destpath;
		if (picFileName != null) {
	         destpath= ServletActionContext.getServletContext().getRealPath(".");
	        System.out.println("Server path:" + destpath);
	        File destFile = new File(destpath, picFileName);
	        try {
	          FileUtils.copyFile(pic, destFile);
	        } catch (IOException e) {
	          System.out.println("error occurred");
	          e.printStackTrace();
	          return ERROR;
	        }
	        
	        FileInputStream inputStream = new FileInputStream(destFile);
	        EditProfileDAO ed=new EditProfileDAOImpl();
	        boolean f=ed.updatePhoto(u.getUserId(), inputStream);
	        if(f){
	        	addActionMessage("Profile Picture has been uploaded successfully.");
	        	return "success";
	        }else{
	    		addActionError("Technical issue occurred ,please try again later.");
	            return "error";
	        }
        }
		addActionError("Technical issue occurred ,please try again later.");
		return "error";
	}
	public String saveChanges(){
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		User u=(User)session.getAttribute("user");
		System.out.println(u.getUserId());
		System.out.println(password);
		EditProfileDAO ed=new EditProfileDAOImpl();
		boolean flag=ed.saveProfileChange(email,password,u.getUserId(),interest,mobno);
		displayAllInterest();
		LoginDAO ld=new LoginDAOimpl();
		String intCSV=ld.getInterest(u.getUserId());
		if(flag){
			u.setInterest(intCSV);
			u.setEmailId(email);
			u.setPassword(password);
			u.setMobno(mobno);
			session.removeAttribute("user");
			session.setAttribute("user", u);
			addActionMessage("Profile has been changed Sucessfully.");
			return "success";
		}
		else {
			addActionError("Technical Error Occurred ,Please try again.");
			return "error";
		}
	}

	public String displayAllInterest(){
		EditProfileDAO ed=new EditProfileDAOImpl();
		ArrayList<Interest> al=ed.getAllInterest();
		request=ServletActionContext.getRequest();
		request.setAttribute("interestList", al);
		return "success";
	}
}
