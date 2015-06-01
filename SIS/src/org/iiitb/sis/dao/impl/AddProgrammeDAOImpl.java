package org.iiitb.sis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.iiitb.sis.dao.AddProgrammeDAO;
import org.iiitb.sis.util.ConnectionPool;

public class AddProgrammeDAOImpl implements AddProgrammeDAO
{
	static Connection con = ConnectionPool.getConnection();
	
	public boolean addProgramme(String programmeName, String noOfTerms)
	{
		boolean flag = true;
		String sql = "select distinct programme from term_details";
		String addPrograme = "insert into term_details values(?,?,?)";
		try 
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if(programmeName.equalsIgnoreCase(rs.getString("programme")))
					return false;
			}
			for(int i=1;i<=Integer.parseInt(noOfTerms);i++)
			{
				String termID = programmeName+"_"+i;
				ps = con.prepareStatement(addPrograme);
				ps.setString(1, termID);
				ps.setString(2, Integer.toString(i));
				ps.setString(3,programmeName);
				int check = ps.executeUpdate();
				if(check <= 0)
					flag = false;
			}
			if(flag)
				return true;
			else
				return false;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
