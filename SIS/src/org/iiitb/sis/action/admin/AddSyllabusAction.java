package org.iiitb.sis.action.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.iiitb.sis.model.*;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.iiitb.sis.dao.SyllabusDAO;
import org.iiitb.sis.dao.impl.SyllabusDAOImpl;

import com.opensymphony.xwork2.ActionSupport;

public class AddSyllabusAction extends  ActionSupport {
		
		public ArrayList<Subject> subjectLists;
		private String Subject;
		public String Syllabus;
		String errormsg;
		File fileUpload = null;
		String fileUploadContentType;
		String fileUploadFileName;
		private Subject s;									//change
		private String temp_subject;						//change
		private String temp_subject_id;						//change
		
		public File getFileUpload() {
			return fileUpload;
		}
		public void setFileUpload(File fileUpload) {
			this.fileUpload = fileUpload;
		}
		public String getFileUploadContentType() {
			return fileUploadContentType;
		}
		public void setFileUploadContentType(String fileUploadContentType) {
			this.fileUploadContentType = fileUploadContentType;
		}
		public String getFileUploadFileName() {
			return fileUploadFileName;
		}
		public void setFileUploadFileName(String fileUploadFileName) {
			this.fileUploadFileName = fileUploadFileName;
		}
		public String getErrormsg() {
			return errormsg;
		}
		public void setErrormsg(String errormsg) {
			this.errormsg = errormsg;
		}
				
		public ArrayList<Subject> getSubjectLists() {
			return subjectLists;
		}

		public void setSubjectLists(ArrayList<Subject> subjectLists) {
			this.subjectLists = subjectLists;
		}

		public String getSyllabus() {
			return Syllabus;
		}

		public void setSyllabus(String syllabus) {
			Syllabus = syllabus;
		}

		public String getSubject() {
			return Subject;
		}

		public void setSubject(String subject) {
			this.Subject = subject;
		}
		
		
		public String populateCourses(){
			HttpSession session=ServletActionContext.getRequest().getSession(false);
			User u=(User)session.getAttribute("user");
			
			SyllabusDAO subjects = new SyllabusDAOImpl();
			subjectLists = subjects.getAllSubjects(u.getUserId());
			return "success";
		}

		public String execute(){
			System.out.println("file Name..."+fileUploadContentType);
			
			SyllabusDAO syllabus = new SyllabusDAOImpl();
			if(fileUpload == null){
				boolean ans = syllabus.setSyllabus(Subject, Syllabus,0);
					
				 if(ans){
			        	setErrormsg("Syllabus has been updated succesfully");
			        	populateCourses();
			        	return "success";
			     }
		        else{ 
		        	setErrormsg("Some issue occurred");
		        	populateCourses();
		        	return "error";		
		        }
			}
			else{
				String destpath=null;
				 File destFile= null;
				 Date date = null;
				try{
						
					destpath =ServletActionContext.getServletContext().getRealPath("/")+"Assets/Syllabus_Files";
					System.out.println("servlet "+ServletActionContext.getServletContext());
					date = new Date() ;
					System.out.println("Server path: " + destpath +fileUploadFileName+" "+fileUploadFileName+"_"+date);
			        destFile = new File(destpath,fileUploadFileName+"_"+date);
		        
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}
		        try {
		          FileUtils.copyFile(fileUpload, destFile);

		        } catch (IOException e) {
		          // TODO Auto-generated catch block
		          e.printStackTrace();
		        }
				boolean ans = syllabus.setSyllabus(Subject,destpath+"/"+fileUploadFileName+"_"+date,1); 
				 if(ans){
			        	setErrormsg("Syllabus has been updated succesfully");
			        	populateCourses();
			        	return "success";
			     }
		        else{ 
		        	setErrormsg("Some issue occurred");
		        	populateCourses();
		        	return "error";		
		        }
			}
		}
		public String populateSyllabus()						//change
		{
			HttpSession session=ServletActionContext.getRequest().getSession(false);
			User u=(User)session.getAttribute("user");
			
			SyllabusDAO syllabus = new SyllabusDAOImpl();
			System.out.println(Subject);
			
			setS(syllabus.populateSyllabus(Subject));
			setTemp_subject(s.getSubjectName());
			setTemp_subject_id(Subject);
			
			
			SyllabusDAO subjects = new SyllabusDAOImpl();
			subjectLists = subjects.getAllSubjects(u.getUserId());
			int i=0;
			while(i<subjectLists.size())
			{
				if(subjectLists.get(i).getSubjectCode().equalsIgnoreCase(Subject))
					break;
				i++;
			}
			subjectLists.remove(i);
			return "success";
		}
		
		public String getAllSyllabus()					//change
		{
			SyllabusDAO syllabus = new SyllabusDAOImpl();
			setSubjectLists(syllabus.getAllSyllabus());
			return "success";
		}
		
		public Subject getS() {
			return s;
		}
		public void setS(Subject s) {
			this.s = s;
		}
		public String getTemp_subject() {
			return temp_subject;
		}
		public void setTemp_subject(String temp_subject) {
			this.temp_subject = temp_subject;
		}
		public String getTemp_subject_id() {
			return temp_subject_id;
		}
		public void setTemp_subject_id(String temp_subject_id) {
			this.temp_subject_id = temp_subject_id;
		}
}
