package org.iiitb.sis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.sis.dao.AddCourseDAO;
import org.iiitb.sis.model.Terms;
import org.iiitb.sis.util.ConnectionPool;

public class AddCourseDAOImpl implements AddCourseDAO
{
	static Connection con = ConnectionPool.getConnection();
	
	public List<String> getProgramme()
	{
		List<String> programme = new ArrayList<String>();
		String sql = "select distinct programme from term_details";
		programme.add("");
		try
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				programme.add(rs.getString("programme"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return programme;
	}
	
	public List<Terms> getTerms(String programmeName)
	{
		List<Terms> terms = new ArrayList<Terms>();
		String sql = "select term_id,term_name from term_details where programme=?";
		try
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, programmeName);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Terms t = new Terms();
				t.setTerm_id(rs.getString("term_id"));
				t.setTerm_name(rs.getString("term_name"));
				terms.add(t);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return terms;
	}
	
	public String addCourse(String termID, String subjectID, String subjectName, String credits, String programmeName)
	{
		boolean flag = true;
		boolean flag1 = true;
		String verfiy = "select subject_id, term_id from subject_term";
		String check = "select subject_id from subject";
		String addSubject = "insert into subject(subject_id,subject_name,credits) values(?,?,?)";
		String addSubjectTerm = "insert into subject_term(subject_id,term_id) values(?,?)";
		try 
		{
			PreparedStatement ps = con.prepareStatement(check);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if(subjectID.equalsIgnoreCase(rs.getString("subject_id")))
					flag1=false;
			}
			ps = con.prepareStatement(verfiy);
			rs = ps.executeQuery();
			while(rs.next())
			{
				if(subjectID.equalsIgnoreCase(rs.getString("subject_id")) && (rs.getString("term_id").contains(programmeName)))
					return "Course Already Registered for this Programme";
			}
			if(flag1)
			{
				ps = con.prepareStatement(addSubject);
				ps.setString(1, subjectID);
				ps.setString(2, subjectName);
				ps.setString(3, credits);
				int b = ps.executeUpdate();
				if(b <= 0)
					flag = false;
			}
			ps = con.prepareStatement(addSubjectTerm);
			ps.setString(1, subjectID);
			ps.setString(2, termID);
			int b=ps.executeUpdate();
			if(b <= 0)
				flag = false;
			if(flag)
				return "Course successfully added";
			else
				return "Problem in adding course. Please try after sometime.";
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return "Problem in adding course. Please try after sometime.";
	}
}
