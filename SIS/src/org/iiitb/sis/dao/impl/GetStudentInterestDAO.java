package org.iiitb.sis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.iiitb.sis.model.Interest;
import org.iiitb.sis.model.Student;
import org.iiitb.sis.util.ConnectionPool;

public class GetStudentInterestDAO {

	public ArrayList<Interest> populatedropdown()
	{
		Connection con = ConnectionPool.getConnection();
		// TODO Auto-generated method stub
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Interest> Interest=new ArrayList<Interest>();
		Interest in=null;
		try {
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		
		try {
			rs = stmt.executeQuery("select * from interest" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			
			e.printStackTrace();
		}
		if(rs==null)
			System.out.println("eroorrrrrr");
		
		try {
			while(rs.next()){
			    	in =new Interest();
			    	in.setInterestId(rs.getString("interest_id"));
			    	in.setInterestName(rs.getString("interest_name"));
			      Interest.add(in);  
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Interest;
	}
	
	public String getIntname(String intId){
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement preStmt;
		ResultSet rs=null;
		String interestName="";
		try {
			preStmt = conn.prepareStatement("select * from interest where interest_id=?");
			preStmt.setString(1, intId);
			rs = preStmt.executeQuery();
			while(rs.next()){
				interestName=rs.getString("interest_name");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		return interestName;
	}
	
	public ArrayList<Student> getStudents(String interestId){
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement preStmt;
		ResultSet rs=null;
		ArrayList<Student> studentList=new ArrayList<Student>();
		ArrayList<Student> studentListFinal=new ArrayList<Student>();
		Student s=null;
		try {
			preStmt = conn.prepareStatement("select * from user where user_id In(select student_id from student_interest where interest_id In(?))");
			preStmt.setString(1,interestId);
			rs = preStmt.executeQuery();
			int count=1;
			while(rs.next()){
				s=new Student();
				count++;
				s.setStudent_id(rs.getString("user_id"));
				System.out.println("getStudents-===>"+s.getStudent_id());
				s.setName(rs.getString("name"));
				studentList.add(s);
			}
			if(count>1){
				int count2=1;
				for(int i=0;i<count-1;i++){
					Student s1=studentList.get(i);
					preStmt = conn.prepareStatement("select * from term_details where term_id In(select term_id from student_term where student_id=?)");
					preStmt.setString(1, s1.getStudent_id());
					rs = preStmt.executeQuery();
					Student sf=null;
					while(rs.next()){
						sf=new Student();
						sf.setSno(String.valueOf(count2++));
						sf.setStudent_id(s1.getStudent_id());
						sf.setTerm(rs.getString("term_name"));
						sf.setProg(rs.getString("programme"));
						sf.setName(s1.getName());
						studentListFinal.add(sf);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		return studentListFinal;
	}
}
