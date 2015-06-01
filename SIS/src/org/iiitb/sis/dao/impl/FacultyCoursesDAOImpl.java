package org.iiitb.sis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.iiitb.sis.dao.FacultyCoursesDAO;
import org.iiitb.sis.model.Course;
import org.iiitb.sis.model.EnrolledStudents;
import org.iiitb.sis.model.Subject;
import org.iiitb.sis.util.ConnectionPool;

public class FacultyCoursesDAOImpl implements FacultyCoursesDAO {

	Connection con=ConnectionPool.getConnection();
	PreparedStatement ps;
	@Override
	public ArrayList<Subject> populatedropdown(String facultyid)
	{
		// TODO Auto-generated method stub
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Subject> course=new ArrayList<Subject>();
		Subject s=null;
		try {
			stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		try {
			rs = stmt.executeQuery("select subject_id,subject_name from subject where subject_id IN(select subject_id from faculty_subject where faculty_id='"+facultyid+"')");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		if(rs==null)
			System.out.println("eroorrrrrr");
		
		 try {
			while(rs.next()){
			      s=new Subject();
			      s.setSubjectCode(rs.getString("subject_id"));
			      s.setSubjectName(rs.getString("subject_name"));
			      course.add(s);  
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course;
	}
	
	public String getSubjectName(String subjectId)
	{
		// TODO Auto-generated method stub
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Subject> course=new ArrayList<Subject>();
		String s=null;
		try {
			stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		try {
			rs = stmt.executeQuery("select subject_name from subject where subject_id='"+subjectId+"'");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		if(rs==null)
			System.out.println("eroorrrrrr");
		
		 try {
			if(rs.next()){
			       s=rs.getString("subject_name");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public ArrayList<Subject> getAllFacultyCourses(String facultyid){
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Subject> course=new ArrayList<Subject>();
		try {
			stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		Subject c=null;
		 
		try {
			rs = stmt.executeQuery("select * from subject where subject_id In (select subject_id from faculty_subject where faculty_id='"+facultyid+"')");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		if(rs==null)
			System.out.println("eroorrrrrr");
		
		 try {
			while(rs.next()){
			    c=new Subject();
			    c.setSubjectCode(rs.getString("subject_id"));
			    c.setSubjectName(rs.getString("subject_name"));
			    c.setPoint(rs.getString("credits"));
			      course.add(c);  
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course;
	}
	
	public ArrayList<EnrolledStudents> getEnrolledStudents(String Subject_id)
	{
		// TODO Auto-generated method stub
				Statement stmt=null;
				ResultSet rs=null;
				ArrayList<EnrolledStudents> enrolledStudents=new ArrayList<EnrolledStudents>();
				try {
					stmt = con.createStatement();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					e.printStackTrace();
				} 
				
				 
				try {
					rs = stmt.executeQuery("select sub_enroll_details.student_id,user.name from sub_enroll_details,user where user.user_id=sub_enroll_details.student_id and sub_enroll_details.subject_id='"+Subject_id+"'");
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				if(rs==null)
					System.out.println("eroorrrrrr");
				
				 try {
					while(rs.next()){
						EnrolledStudents e=new EnrolledStudents();
						e.setStudent_id(rs.getString("student_id"));
						e.setStudent_name(rs.getString("name"));
						enrolledStudents.add(e);  
					      
					 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return enrolledStudents;
				
		
		
	}

}
