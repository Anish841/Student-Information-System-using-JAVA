package org.iiitb.sis.dao.impl;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.iiitb.sis.dao.SyllabusDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.sis.model.Subject;
import org.iiitb.sis.util.ConnectionPool;

public class SyllabusDAOImpl implements SyllabusDAO {


	@Override
	public boolean setSyllabus(String courseName, String syllabus,int type) {
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps;
		int ans2 = 0;
		int ans = 0;
		
		//update syllabus
		try {
			ps = con.prepareStatement("Update subject set syllabus= ? where subject_id= ?");
			ps.setString(1, syllabus);
			ps.setString(2, courseName);
			ans = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		//update flag for syllabus
		try {
			ps = con.prepareStatement("Update subject set syllabus_type= ? where subject_id= ?");
			ps.setInt(1, type);
			ps.setString(2, courseName);
			ans2 = ps.executeUpdate();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionPool.freeConnection(con);
		}	
		
		//check for successful update of both attributes
		if(ans2 > 0 && ans > 0){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public ArrayList<Subject> getAllSubjects(String facultyId) {
		
		ArrayList<Subject> sub = new ArrayList<Subject>();
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps;
		Subject s=null;
		ResultSet rs= null;
		try {
			ps = con.prepareStatement("select faculty_subject.subject_id,subject.subject_name from faculty_subject,subject where faculty_subject.subject_id=subject.subject_id and faculty_id=?");
			ps.setString(1, facultyId);
			rs = ps.executeQuery();
			while(rs.next()){
				s=new Subject();
				s.setSubjectCode(rs.getString("subject_id"));
				s.setSubjectName(rs.getString("subject_name"));
				sub.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionPool.freeConnection(con);
		}	
		
		
		// TODO Auto-generated method stub
		return sub;
	}


	@Override
	public Subject populateSyllabus(String subject) {
		// TODO Auto-generated method stub
		System.out.println(subject);
		Subject sub = new Subject(); 
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps;
		Subject s=null;
		ResultSet rs= null;
		try {
			ps = con.prepareStatement("select subject.syllabus,subject.syllabus_type,subject.subject_name from subject where subject_id=?");
			ps.setString(1, subject);
			rs = ps.executeQuery();
			while(rs.next()){
				sub = new Subject();
				sub.setSyllabus_text(rs.getString("syllabus"));
				sub.setSyllabus_type(String.valueOf(rs.getInt("syllabus_type")));
				
				System.out.println("sub syll===="+sub.getSyllabus_type());
				System.out.println("type=="+sub.getSyllabus_type());
				
				sub.setSubjectName(rs.getString("subject_name"));
				if(sub.getSyllabus_type().equalsIgnoreCase("1"))
				{
					String name = sub.getSyllabus_text();
					name = name.substring(sub.getSyllabus_text().lastIndexOf("/")+1);
					//System.out.println("name===="+name);
					sub.setSyllabus_text("Assets/Syllabus_Files/"+name);
					//System.out.println(sub.getSyllabus_text());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionPool.freeConnection(con);
		}	
		
		// TODO Auto-generated method stub
		return sub;
		
	}

	@Override
	public ArrayList<Subject> getAllSyllabus() {
		// TODO Auto-generated method stub
		//System.out.println(subject);
		ArrayList<Subject> subjectList = new ArrayList<Subject>();
		Subject sub = new Subject(); 
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps;
		Subject s=null;
		ResultSet rs= null;
		try {
			ps = con.prepareStatement("select subject.syllabus,subject.syllabus_type,subject.subject_name,subject.subject_id from subject");
			//ps.setString(1, subject);
			rs = ps.executeQuery();
			while(rs.next()){
				sub = new Subject();
				sub.setSubjectCode(rs.getString("subject_id"));
				sub.setSyllabus_text(rs.getString("syllabus"));
				sub.setSyllabus_type(String.valueOf(rs.getInt("syllabus_type")));
				sub.setSubjectName(rs.getString("subject_name"));
				if(sub.getSyllabus_type().equalsIgnoreCase("1"))
				{
					String name = sub.getSyllabus_text();
					name = name.substring(sub.getSyllabus_text().lastIndexOf("/")+1);
					sub.setSyllabus_text("Assets/Syllabus_Files/"+name);
				}
				subjectList.add(sub);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionPool.freeConnection(con);
		}	
		
		// TODO Auto-generated method stub
		return subjectList;
		
	}
}
