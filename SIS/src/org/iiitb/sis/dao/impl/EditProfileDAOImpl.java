package org.iiitb.sis.dao.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.sis.dao.EditProfileDAO;
import org.iiitb.sis.model.Interest;
import org.iiitb.sis.util.ConnectionPool;

public class EditProfileDAOImpl implements EditProfileDAO{

	private String getAllInterestQuery="select * from interest";
	private String updateProfileQuery="update user set email_id=?,password=?,mobile_no=? where user_id=?";
	private String deleteInterest="delete from student_interest where student_id=?";
	private String insertInterest="insert into student_interest(student_id,interest_id) values(?,?)";
	private String updatePhoto="update user set photo=? where user_id=?";
	@Override
	public ArrayList<Interest> getAllInterest() {
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement preStmt;
		ArrayList<Interest> interest=new ArrayList<Interest>();
		Interest i;
		try
		{
			preStmt = conn.prepareStatement(getAllInterestQuery);
			ResultSet rs=preStmt.executeQuery();
			while(rs.next()){
				i=new Interest();
				i.setInterestId(rs.getString("interest_id"));
				i.setInterestName(rs.getString("interest_name"));
				interest.add(i);
			}
			rs.close();
		
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(conn);
		}
		return interest;
		
	}
	
	@Override
	public boolean saveProfileChange(String emailId,String password,String userId,List<String> interestList,String mobno) {
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement preStmt;
		Interest i;
		try
		{
			preStmt = conn.prepareStatement(updateProfileQuery);
			preStmt.setString(1, emailId);
			preStmt.setString(2, password);
			preStmt.setString(3, mobno);
			preStmt.setString(4, userId);
			int n=preStmt.executeUpdate();
			
			preStmt=conn.prepareStatement(deleteInterest);
			preStmt.setString(1, userId);
			int n1=preStmt.executeUpdate();
			
			for(int i1=0;interestList!=null && i1<interestList.size();i1++){
				preStmt=conn.prepareStatement(insertInterest);
				preStmt.setString(1, userId);
				preStmt.setString(2, interestList.get(i1));
				preStmt.executeUpdate();
			}
			return true;
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		} finally
		{
			ConnectionPool.freeConnection(conn);
		}
	
	}

	@Override
	public boolean updatePhoto(String userId,InputStream pic) {
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement preStmt;
		try
		{
		
			preStmt = conn.prepareStatement(updatePhoto);
			preStmt.setBlob(1, pic);
			preStmt.setString(2, userId);
			int n=preStmt.executeUpdate();
			if(n>0)
			return true;
			else 
				return false;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		} finally
		{
			ConnectionPool.freeConnection(conn);
		}
}

}
