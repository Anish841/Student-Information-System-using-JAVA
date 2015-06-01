package org.iiitb.sis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.sis.dao.AdminFacultyFromSubjectsDAO;
import org.iiitb.sis.model.User;
import org.iiitb.sis.model.Subject;
import org.iiitb.sis.util.ConnectionPool;

public class AdminFacultyFromSubjectsDAOImpl implements AdminFacultyFromSubjectsDAO {

	@Override
	public ArrayList<Subject> getAllSubjects() {
		ArrayList<Subject> sub = new ArrayList<Subject>();
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps;
		Subject s=null;
		ResultSet rs= null;
		try {
			ps = con.prepareStatement("select subject.subject_id,subject.subject_name from subject");
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
	public ArrayList<User> getAllFaculties(String subject_id) {
		ArrayList<User> faculty = new ArrayList<User>();
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps;
		User s=null;
		ResultSet rs= null;
		try {
			ps = con.prepareStatement("select user_id,name,email_id,mobile_no,address,dob from user where user_id in (select faculty_id from faculty_subject where subject_id = ?)");
			ps.setString(1, subject_id);
			rs = ps.executeQuery();
			while(rs.next()){
				s = new User();
				s.setUserId(rs.getString("user_id"));
				s.setName(rs.getString("name"));
				s.setEmailId(rs.getString("email_id"));
				s.setMobno(rs.getString("mobile_no"));
				s.setAddress(rs.getString("address"));
				s.setDob(rs.getString("dob"));
				faculty.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionPool.freeConnection(con);
		}	
		
		
		// TODO Auto-generated method stub
		return faculty;

	}

}
