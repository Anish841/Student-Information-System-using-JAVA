package org.iiitb.sis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.iiitb.sis.dao.TermSubjectDAO;
import org.iiitb.sis.model.TermSubject;
import org.iiitb.sis.util.ConnectionPool;

public class TermSubjectDAOImpl implements TermSubjectDAO{
	
	Connection con=ConnectionPool.getConnection();
	PreparedStatement ps;
	@Override
	public ArrayList<String> populatedropdown()
	{
		// TODO Auto-generated method stub
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<String> programme=new ArrayList<String>();
		
		try {
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		
		try {
			rs = stmt.executeQuery("select distinct programme from term_details" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			
			e.printStackTrace();
		}
		if(rs==null)
			System.out.println("eroorrrrrr");
		
		try {
			while(rs.next()){
			    
			      programme.add(rs.getString("programme"));  
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return programme;
	}
		
	public ArrayList<TermSubject> getTermSubject(String programme)
	{
		// TODO Auto-generated method stub
				Statement stmt=null;
				ResultSet rs=null;
				ArrayList<TermSubject> termsubject=new ArrayList<TermSubject>();
				try {
					stmt = con.createStatement();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					e.printStackTrace();
				} 
				
				try {
					rs = stmt.executeQuery("select A.term_id,A.term_name,A.subject_id,subject.subject_name from subject,(select term_details.term_id,term_details.term_name,subject_term.subject_id from term_details,subject_term where subject_term.term_id = term_details.term_id AND term_details.programme='"+programme+"') as A where A.subject_id=subject.subject_id order by term_name ASC");	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				if(rs==null)
					System.out.println("eroorrrrrr");
				
				try {
					while(rs.next()){
						System.out.println("query");
						TermSubject e=new TermSubject();
						e.setTerm_name(rs.getString("term_name"));
						e.setSubject_id(rs.getString("subject_id"));
						e.setSubject_name(rs.getString("subject_name"));
						System.out.println(e.getTerm_name());
						System.out.println(e.getSubject_id());
						System.out.println(e.getSubject_name());
						termsubject.add(e); 
						
					      
					 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return termsubject;
	}
}
