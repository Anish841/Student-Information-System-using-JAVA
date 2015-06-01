package org.iiitb.sis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.sis.dao.NewsDAO;
import org.iiitb.sis.model.News;
import org.iiitb.sis.util.ConnectionPool;

public class NewsDAOImpl implements NewsDAO {
	
	static Connection con = ConnectionPool.getConnection();
	@Override
	public List<News> getNews() {
		// TODO Auto-generated method stub\
		List<News> list =  new ArrayList<News>();
		String sql = "select * from news";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				News news = new News();
				news.setNewsId(Integer.parseInt(rs.getString("news_id")));
				news.setPublishedDate(rs.getString("news_date"));
				news.setHeadLine(rs.getString("news_name"));
				news.setDetailedNews(rs.getString("news_details"));
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//ConnectionPool.freeConnection(con);
		}
		return list;
	}

	@Override
	public boolean saveNews(News news) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "insert into news(news_name,news_details,news_date) values(?,?,?)";
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, news.getHeadLine());
			ps.setString(2, news.getDetailedNews());
			ps.setString(3,news.getPublishedDate());
			ps.execute();
			result = true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			//ConnectionPool.freeConnection(con);
		}
		return result;
	}

	@Override
	public boolean deleteNews(List<String> list) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "delete from news where news_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			for (String s : list)
			{
				ps.setInt(1, Integer.parseInt(s));
				ps.execute();
			}
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//ConnectionPool.freeConnection(con);
		}
		return result;
	}

}
