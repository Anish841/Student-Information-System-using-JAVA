package org.iiitb.sis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.sis.dao.EnrolledSubjectDAO;
import org.iiitb.sis.dao.EnrollmentDAO;
import org.iiitb.sis.util.ConnectionPool;

public class EnrolledSubjectDAOImpl implements EnrolledSubjectDAO {

	@Override
	public String enrolledsubjects(String id,ArrayList subject) {
		// TODO Auto-generated method stub
		//System.out.println("connection in enrolledsubjects method: "+connection);
		    int k=0;
		    String result=null;
		    Connection connection=null;
		try {
			System.out.println("hi ES");
			connection = ConnectionPool.getConnection();
			 
			PreparedStatement ps1;
			PreparedStatement ps2;
			System.out.println("hello ES");
			String sub="";
	        if(subject!=null){
	        	for(int i=0;i<subject.size();i++){
	        		
	        			sub=subject.get(i).toString();
	        			ps1=connection.prepareStatement("select subject_id from subject where subject_name='"+sub+"'");
	        			
	        			ResultSet rs1=ps1.executeQuery();
	        			while(rs1.next()){
	        			ps2=connection.prepareStatement("insert into sub_enroll_details(subject_id,student_id) values('"+rs1.getString("subject_id")+"',?)");
	        			ps2.setString(1, id);
	        			k=ps2.executeUpdate();
	        			k++;
	        			}
	        	}
			} 
	        System.out.println("data inserted");
	        if(k!=0)
	        {
	        	result="success";
	        	return result;
	        }
		}
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		finally{
			ConnectionPool.freeConnection(connection);
		}

		return result;
	}
	
	

}
