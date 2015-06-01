package org.iiitb.sis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.sis.dao.*;
import org.iiitb.sis.model.*;
import org.iiitb.sis.util.*;

public class AnnouncementsDAOImpl implements AnnouncementsDAO {
	
	static Connection con = ConnectionPool.getConnection();
	@Override
	public List<Announcements> getAnnouncements()
	{
		List<Announcements> list =  new ArrayList<Announcements>();
		String sql = "select * from announcement";
		try
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Announcements announcements = new Announcements();
				announcements.setAnnouncementsId(Integer.parseInt(rs.getString("announcements_id")));
				announcements.setPublishedDate(rs.getString("announcements_date"));
				announcements.setHeadLine(rs.getString("announcements_name"));
				announcements.setDetailedAnnouncements(rs.getString("announcements_details"));
				list.add(announcements);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//ConnectionPool.freeConnection(con);
		}
		return list;
	}

	@Override
	public boolean saveAnnouncements(Announcements announcements)
	{
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "insert into announcement(announcements_name,announcements_details,announcements_date) values(?,?,?)";  //announcements_details = 1000 announcements_name=1000
		try
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, announcements.getHeadLine());
			ps.setString(2, announcements.getDetailedAnnouncements());
			ps.setString(3,announcements.getPublishedDate());
			ps.execute();
			result = true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			//ConnectionPool.freeConnection(con);
		}
		return result;
	}

	@Override
	public boolean deleteAnnouncements(List<String> list)
	{
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "delete from announcement where announcements_id = ?";
		try
		{
			PreparedStatement ps = con.prepareStatement(sql);
			for (String s : list)
			{
				ps.setInt(1, Integer.parseInt(s));
				ps.execute();
			}
			result = true;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//ConnectionPool.freeConnection(con);
		}
		return result;
	}

}
