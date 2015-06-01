package org.iiitb.sis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.sis.dao.EnrollmentDAO;

public class EnrollmentDAOImpl implements EnrollmentDAO {
	private ArrayList<String> subject_id= new ArrayList<String>();
	
	public ArrayList enroll(String id,Connection connection,ArrayList<String> temp_subject) {
		System.out.println("connection: "+connection);
		
		
		try {
			System.out.println("hi");
			//can enroll only in those subjects in which dat particular student have not yet registered
		PreparedStatement ps=connection.prepareStatement("select s.subject_name,s.subject_id from subject as s,subject_term as st,student_term as sdt where s.subject_id=st.subject_id and st.term_id=sdt.term_id and sdt.student_id=? and s.subject_name not in (select s.subject_name from sub_enroll_details as sed,subject as s where sed.subject_id=s.subject_id and sed.student_id=?)");
		ps.setString(1,id);
		ps.setString(2,id);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()){
			System.out.println(rs.getString("subject_name"));
			
			temp_subject.add(rs.getString("subject_name"));
			subject_id.add(rs.getString("subject_id"));
		}
		//return temp_subject;
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		return temp_subject;
	
}
	public String enrolledsubjects(Connection connection,ArrayList add_subject){
					return "success";
	}
}
