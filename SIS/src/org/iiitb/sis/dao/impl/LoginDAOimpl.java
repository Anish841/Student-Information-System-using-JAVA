package org.iiitb.sis.dao.impl;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.sis.dao.LoginDAO;
import org.iiitb.sis.dao.NotificationDAO;
import org.iiitb.sis.model.Notification;
import org.iiitb.sis.model.User;
import org.iiitb.sis.util.*;

public class LoginDAOimpl implements LoginDAO {
	
	String getUser="select * from user where user_id=?";
	String updateLogoutTime="update user set last_login=? where user_id=?";
	String getTermDetails="select term_id from student_term where student_id=? order by term_id desc";
	String getInterest="select interest_name from interest where interest_id IN(select interest_id from student_interest where student_id=?);";
	String getPic="select photo from user where user_id=?";
	@Override
	public User isValidUser(User user) {
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement preStmt;
		try
		{
			preStmt = conn.prepareStatement(getUser);
			System.out.println(getUser);
			System.out.println(preStmt);
			System.out.println(user.getUserId());
			preStmt.setString(1, user.getUserId());
			ResultSet result = preStmt.executeQuery();
			if (result.next())
			{
				System.out.println(result.getString("password"));
				if (!user.getPassword().equals(result.getString("password")))
				{
					user.setErrorFlag(true);
					user.setErrorMessage(Constant.Invalid_login);
					return user;
				}
				else
				{
					user.setUserId(result.getString("user_id"));
					user.setEmailId(result.getString("email_id"));
					user.setName(result.getString("name"));
					user.setUserType(result.getString("user_type"));
					user.setLastLoggedOn(result.getString("last_login"));
					user.setGender(result.getString("gender"));
					user.setMobno(result.getString("mobile_no"));
					user.setAddress(result.getString("address"));
					user.setDob(result.getString("dob"));
					user.setPassword(result.getString("password"));
					if (user.getUserType().equalsIgnoreCase("A")){
						user.setErrorFlag(false);
						user.setErrorMessage("SUCCESS");
						return user;
					}else if(user.getUserType().equalsIgnoreCase("F")){
						user.setErrorFlag(false);
						user.setErrorMessage("SUCCESS");
						return user;
					}
					else
					{
							
							String term=getTermDetails(user.getUserId());
							String str[] = null;
							if(term!=null){
								str=term.split("_");
							}
							if(str[0]!=null && str[1]!=null)
								term=str[0]+"& Term= "+str[1];
							else
								term="Data Not Found";
								
							user.setTerm(term);
							
							String interest=getInterest(user.getUserId());
							user.setInterest(interest);
							
					}
				}
			}
			else
			{
				user.setErrorFlag(true);
				user.setErrorMessage(Constant.Invalid_login);
				return user;
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(conn);
		}
		
		return user;
	}
	
	public String getTermDetails(String userId){
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement preStmt;
		String str="";
		try
		{
			preStmt = conn.prepareStatement(getTermDetails);
			preStmt.setString(1, userId);
			ResultSet rs=preStmt.executeQuery();
			if(rs.next()){
				str=rs.getString("term_id");
			}
			rs.close();
			System.out.println(str);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(conn);
		}
		return str;
	}
	@Override
	public boolean updateLogoutTime(String userId,String date) {
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement preStmt;
		try
		{
			System.out.println(userId+" "+date);
			preStmt = conn.prepareStatement(updateLogoutTime);
			preStmt.setString(1, date);
			preStmt.setString(2, userId);
			int n=preStmt.executeUpdate();
			System.out.println(n);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(conn);
		}
		return true;
	}

	@Override
	public String getInterest(String userId) {
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement preStmt;
		String str="";
		try
		{
			preStmt = conn.prepareStatement(getInterest);
			preStmt.setString(1, userId);
			ResultSet rs=preStmt.executeQuery();
			while(rs.next()){
				str+=rs.getString("interest_name")+",";
			}
			if(str.length()>=1)
				str=str.substring(0,str.length()-1);
			rs.close();
			System.out.println(str);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(conn);
		}
		return str;
	}	
	public InputStream displayphoto(String userId){
		Connection conn = ConnectionPool.getConnection();
		InputStream pic=null;
		try
		{
			PreparedStatement stmt = conn.prepareStatement(getPic);
			stmt.setString(1, userId);

			ResultSet rs = stmt.executeQuery();
			
			if (rs.next())
			{
				pic=rs.getBinaryStream("photo");
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.freeConnection(conn);
		}
		return pic;

	}
	
}
