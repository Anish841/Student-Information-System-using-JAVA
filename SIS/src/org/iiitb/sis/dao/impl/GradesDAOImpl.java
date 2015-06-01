package org.iiitb.sis.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.sis.dao.NewsDAO;
import org.iiitb.sis.dao.GradesDAO;
import org.iiitb.sis.model.News;
import org.iiitb.sis.model.Student;
import org.iiitb.sis.model.Course;
import org.iiitb.sis.model.Grade;
import org.iiitb.sis.util.ConnectionPool;

public class GradesDAOImpl implements GradesDAO{
	static Connection con = ConnectionPool.getConnection();
	
	public List<String> getStudent(String userId)
	{
		List<String> studentlist=new ArrayList<String>();
		studentlist.add("");
		String sql="select distinct(sub_enroll_details.student_id) from sub_enroll_details,faculty_subject where faculty_subject.faculty_id=? and faculty_subject.subject_id=sub_enroll_details.subject_id";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				studentlist.add(rs.getString("student_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//ConnectionPool.freeConnection(con);
		}
		return studentlist;
	}
	public List<String> getGrade()
	{
		List<String> gradelist=new ArrayList<String>();
		gradelist.add(" ");
		String sql = "select name from grade";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				
				gradelist.add(rs.getString("name"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//ConnectionPool.freeConnection(con);
		}
		return gradelist;
	}
	
	public List<String> getCourse(String roll,String facultyID)
	{
		List<String> courselist=new ArrayList<String>();
		courselist.add(" ");
		String sql9="select subject_name from subject where subject_id IN(select subject_id from faculty_subject where faculty_id=? and subject_id In(select subject_id from subject where subject_id IN(select subject_id from sub_enroll_details where student_id=?)))";
		try {
			PreparedStatement ps = con.prepareStatement(sql9);
			ps.setString(1, facultyID);
			ps.setString(2, roll);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				courselist.add(rs.getString("subject_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//ConnectionPool.freeConnection(con);
		}
		return courselist;
	}
	public String viewAssignmentHistory(String roll,String course)
	{
		String str=null;
		System.out.println("Inside view Assignemnt History!");
		System.out.println("Roll="+roll+",Course="+course);
		String sql99="select subject_id from subject where subject_name=?";
		String sql9="select grade from sub_enroll_details where student_id=? and subject_id=?";
		try
		{
			
			PreparedStatement ps = con.prepareStatement(sql99);
			PreparedStatement ps1 = con.prepareStatement(sql9);
			ps.setString(1,course);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ps1.setString(1,roll);
				ps1.setString(2,rs.getString("subject_id"));
				ResultSet rs1=ps1.executeQuery();
				while(rs1.next())
				{
					str=(rs1.getString("grade"));
					System.out.println("Hello,got your grades!");
					return str;
					
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//ConnectionPool.freeConnection(con);
		}
		
        
        
		return str;
	}
	
	public boolean addGrade(String roll,String course,String grade ) {
		// TODO Auto-generated method stub
		boolean result = false;
		String subid=null;
		String points="";
		String sql1="select subject_id from subject where subject_name=?";
		String sql2="select points from grade where name=?";
		try{
			PreparedStatement ps = con.prepareStatement(sql1);
			ps.setString(1,course);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				subid=rs.getString("subject_id");
				break;
			}rs.close();
			
			ps=con.prepareStatement(sql2);
			ps.setString(1, grade);
			rs=ps.executeQuery();
			if(rs.next()){
				points=rs.getString("points");
			}
			rs.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			//ConnectionPool.freeConnection(con);
		}
		
		
		
		
		String sql = "update sub_enroll_details set grade=?,pointer=? where subject_id=? and student_id=?";
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,grade);
			ps.setString(2, points);
			ps.setString(3,subid);
			ps.setString(4,roll);
			int k=ps.executeUpdate();
			if(k==1)
			result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			//ConnectionPool.freeConnection(con);
		}
		return result;
	}
	

}
