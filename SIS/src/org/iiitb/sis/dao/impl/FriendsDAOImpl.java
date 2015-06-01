package org.iiitb.sis.dao.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.sis.dao.FriendsDAO;
import org.iiitb.sis.dao.LoginDAO;
import org.iiitb.sis.model.User;
import org.iiitb.sis.util.ConnectionPool;

public class FriendsDAOImpl implements FriendsDAO {

	@Override
	public ArrayList<User> getAllFriends(String userid) {
		
		ArrayList<User> userDetails = new ArrayList<User>();
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps;
		LoginDAO ld=new LoginDAOimpl();
		ResultSet rs= null;
		try {
			ps = con.prepareStatement("Select * from user where user_id in (select student_id2 from student_friends where student_id1=?)");
			ps.setString(1, userid);
			rs = ps.executeQuery();
			while(rs.next()){
				User u = new User();
				u.setAddress(rs.getString("address"));
				u.setDob(rs.getString("dob"));
				u.setUserId(rs.getString("user_id"));
				u.setName(rs.getString("name"));
				u.setUserType(rs.getString("user_type"));
				u.setEmailId(rs.getString("email_id"));
				u.setLastLoggedOn(rs.getString("last_login"));
				u.setMobno(rs.getString("mobile_no"));
				u.setGender(rs.getString("gender"));
				InputStream binaryStream = rs.getBinaryStream("photo");
				String term=ld.getTermDetails(userid);
				if(term.length()>=2){
					term=term.substring(2,term.length());
				}
				term="Master Of Technology & Term= "+term;
				u.setTerm(term);
				u.setInterest(ld.getInterest(userid));
				
				u.setFriend(true);
				
				userDetails.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionPool.freeConnection(con);
		}	
		// TODO Auto-generated method stub
		return userDetails;
	}
	
	
	@Override
	public User getSingleUser(String userid) {
	
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps;
		User u = new User();
		ResultSet rs= null;
		try {
			ps = con.prepareStatement("Select * from user where user_id =?");
			ps.setString(1, userid);
			rs = ps.executeQuery();
			LoginDAO ld=new LoginDAOimpl();
			while(rs.next()){
				u.setAddress(rs.getString("address"));
				u.setDob(rs.getString("dob"));
				u.setUserId(rs.getString("user_id"));
				u.setName(rs.getString("name"));
				u.setUserType(rs.getString("user_type"));
				u.setEmailId(rs.getString("email_id"));
				u.setLastLoggedOn(rs.getString("last_login"));
				u.setMobno(rs.getString("mobile_no"));
				u.setGender(rs.getString("gender"));
				InputStream binaryStream = rs.getBinaryStream("photo");
				
				u.setFriend(true);
				String term=ld.getTermDetails(userid);
				if(term.length()>=2){
					term=term.substring(2,term.length());
				}
				term="Master Of Technology & Term= "+term;
				u.setTerm(term);
				u.setInterest(ld.getInterest(userid));
				u.setFriend(true);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionPool.freeConnection(con);
		}	
		// TODO Auto-generated method stub
		return u;
	}
	
	
	
	

	@Override
	public String getLastLogin(String userid) {
		
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps;
		String last_login=null;
		
		ResultSet rs= null;
		try {
			ps = con.prepareStatement("Select last_login from user where user_id=?");
			ps.setString(1, userid);
			rs = ps.executeQuery();
			while(rs.next()){
				last_login = rs.getString("last_login");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionPool.freeConnection(con);
		}	
		
		
		// TODO Auto-generated method stub
		return last_login;
	}


	@Override
	public User SearchUser(String userid,String sessionId) {
		Connection con = ConnectionPool.getConnection();
		Connection con1 = ConnectionPool.getConnection();
		PreparedStatement ps,ps1;
		User u = new User();
		int count=0;
		ResultSet rs= null;
		ResultSet rs1=null;
		try {
			ps = con.prepareStatement("Select * from user where user_id =?");
			ps.setString(1, userid);
			rs = ps.executeQuery();
			
			LoginDAO ld=new LoginDAOimpl();
			while(rs.next()){
				count++;
				u.setAddress(rs.getString("address"));
				u.setDob(rs.getString("dob"));
				u.setUserId(rs.getString("user_id"));
				u.setName(rs.getString("name"));
				u.setUserType(rs.getString("user_type"));
				u.setEmailId(rs.getString("email_id"));
				u.setLastLoggedOn(rs.getString("last_login"));
				u.setMobno(rs.getString("mobile_no"));
				u.setGender(rs.getString("gender"));
				InputStream binaryStream = rs.getBinaryStream("photo");
				String term=ld.getTermDetails(userid);
				if(term.length()>=2){
					term=term.substring(2,term.length());
				}
				term="Master Of Technology & Term= "+term;
				u.setTerm(term);
				u.setInterest(ld.getInterest(userid));
			}
			if(count==0)
			{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionPool.freeConnection(con);
		}	
		
		try {
			
			ps1 = con1.prepareStatement("Select * from student_friends where student_id1=? and student_id2=?");
			ps1.setString(2, userid);
			ps1.setString(1, sessionId);
			rs1 = ps1.executeQuery();
			
			boolean flag=false;
			while(rs1.next()){
				System.out.println("FRIENDS DAO====>setting it to true");
				u.setFriend(true);
				flag=true;
				}
				if(!flag)
				{
					u.setFriend(false);
				}
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionPool.freeConnection(con1);
		}	
		// TODO Auto-generated method stub
		return u;
		
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean addFriend(String id1, String id2) {
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("insert into student_friends values(?,?)");
			ps.setString(1, id1);
			ps.setString(2, id2);
			int n=ps.executeUpdate();
			
			ps=con.prepareStatement("insert into student_friends values(?,?)");
			ps.setString(1, id2);
			ps.setString(2, id1);
			int n1=ps.executeUpdate();
			
			if(n>0 && n1>0)
				return true;
			else 
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionPool.freeConnection(con);
		}	
		return false;
	}


	@Override
	public boolean removeFriend(String id1, String id2) {
		Connection con = ConnectionPool.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("delete from student_friends where student_id1=? and student_id2=?");
			ps.setString(1, id1);
			ps.setString(2, id2);
			int n=ps.executeUpdate();
			
			ps=con.prepareStatement("delete from student_friends where student_id1=? and student_id2=?");
			ps.setString(1, id2);
			ps.setString(2, id1);
			int n1=ps.executeUpdate();
			
			if(n>0 && n1>0)
				return true;
			else 
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionPool.freeConnection(con);
		}	
		return false;
	}
	
	

}
