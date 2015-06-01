package org.iiitb.sis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.iiitb.sis.dao.AdminSubjectfromFacultyDAO;
import org.iiitb.sis.model.Subject;
import org.iiitb.sis.model.User;
import org.iiitb.sis.util.ConnectionPool;

public class AdminSubjectfromFacultyDAOImpl implements AdminSubjectfromFacultyDAO 
{
	
	Connection con=ConnectionPool.getConnection();
	PreparedStatement ps;
	@Override
	public ArrayList<User> populatedropdown() 
	{
		// TODO Auto-generated method stub
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<User> user=new ArrayList<User>();
		
		try {
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		
		 
		try {
			rs = stmt.executeQuery("select user_id,name from user where user_type='F'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		if(rs==null)
			System.out.println("eroorrrrrr");
		
		 try {
			while(rs.next())
			{
				User f=new User();
				
				f.setUserId((rs.getString("user_id")));
				f.setName((rs.getString("name")));
			    user.add(f);  
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
		
		
	}
	
	
	public ArrayList<Subject> getSubjectInfo(String Faculty_id)
	{
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Subject> subjectinfo=new ArrayList<Subject>();
		
		try {
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		
		 
		try {
			rs = stmt.executeQuery("select subject_id,subject_name from subject where subject_id in(select distinct subject_id from faculty_subject where faculty_id='"+Faculty_id+"')");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		if(rs==null)
			System.out.println("eroorrrrrr");
		
		 try {
			while(rs.next())
			{
				Subject s=new Subject();
				s.setSubjectCode(rs.getString("subject_id"));
				s.setSubjectName(rs.getString("subject_name"));
				subjectinfo.add(s);
				/*
				f.setUserId((rs.getString("user_id")));
				f.setName((rs.getString("name")));
			    user.add(f);*/  
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return subjectinfo;
		
		
		
		
	}
	

}
