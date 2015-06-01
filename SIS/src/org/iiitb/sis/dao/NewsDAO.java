package org.iiitb.sis.dao;

import java.util.List;

import org.iiitb.sis.model.News;

public interface NewsDAO {
	
	public List<News> getNews();
	public boolean saveNews(News news);
	public boolean deleteNews(List<String> list);	
}
