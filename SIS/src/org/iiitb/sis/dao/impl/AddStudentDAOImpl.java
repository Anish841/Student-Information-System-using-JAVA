package org.iiitb.sis.dao.impl;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.iiitb.sis.dao.AddStudentDAO;

public class AddStudentDAOImpl implements AddStudentDAO {

	@Override
	public boolean addstudent(Connection connection, String id, String name,
			String pwd, String email, String mob, String add, String dob,
			String gender,String usertype,String termId) {
		System.out.println("connection: "+connection);
		System.out.println("id: "+id);
		System.out.println("name: "+name);
		System.out.println("pwd: "+pwd);
		System.out.println("email: "+email);
		System.out.println("mob: "+mob);
		System.out.println("add: "+add);
		System.out.println("dob: "+dob);
		System.out.println("gender: "+gender);
		System.out.println("usertype: "+usertype);
		try {
			PreparedStatement ps=connection.prepareStatement("insert into user (user_id,password,name,email_id,mobile_no,address,dob,gender,user_type) values('"+id+"','"+pwd+"','"+name+"','"+email+"','"+mob+"','"+add+"','"+dob+"','"+gender+"','"+usertype+"')");
			ps.executeUpdate();
			if(usertype!=null && usertype.equalsIgnoreCase("U")){
				ps=connection.prepareStatement("insert into student(student_id) values('"+id+"')");
				ps.executeUpdate();
				System.out.println("term_id===>"+termId);
				ps=connection.prepareStatement("insert into student_term(term_id,student_id) values(?,?)");
				ps.setString(1, termId);
				ps.setString(2, id);
				ps.executeUpdate();
				
			}else if(usertype!=null && usertype.equalsIgnoreCase("F")){
				ps=connection.prepareStatement("insert into faculty(faculty_id) values('"+id+"')");
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
