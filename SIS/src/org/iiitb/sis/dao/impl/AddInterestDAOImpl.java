package org.iiitb.sis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.iiitb.sis.util.ConnectionPool;

public class AddInterestDAOImpl {

	public boolean addInterest(String interestName, String intDetails)
	{
		boolean flag = true;
		Connection con = ConnectionPool.getConnection();
		String addInt = "insert into interest values(?,?,?);";
		try 
		{
			PreparedStatement ps = con.prepareStatement(addInt);
			ps.setString(1, interestName);
			ps.setString(2,interestName);
			ps.setString(3, intDetails);
			int n=ps.executeUpdate();
			
			if(n>0)
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
