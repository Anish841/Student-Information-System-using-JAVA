package org.iiitb.sis.action.admin;

import java.util.Date;
import java.util.List;

import org.iiitb.sis.dao.NewsDAO;
import org.iiitb.sis.dao.impl.NewsDAOImpl;
import org.iiitb.sis.model.News;

import com.opensymphony.xwork2.ActionSupport;

public class NewsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	News news = new News();
	private int newsId;
	private String headLine;
	private String detailedNews;
	private String publishedDate;
	NewsDAO item =  new NewsDAOImpl();
	private List<News> newsList;
	private List<String> name;
	private String msg;
	
	public String fetchNews(){
		System.out.println("fetchNews()");
		setNewsList(item.getNews());
		System.out.println("successful in fetching news");
		return SUCCESS;
	}
	
	public String addNews(){
		System.out.println(new Date().toString());
		news.setHeadLine(getHeadLine());
		news.setDetailedNews(getDetailedNews());
		news.setPublishedDate(new Date().toString());
		news.setNewsId(1);
		boolean res = item.saveNews(news);
		if(res){
			System.out.println("successful in insertig news");
			setMsg("true");
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	public String deleteNews(){
		boolean res = item.deleteNews(getName());
		if(res){
			setMsg("true");
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getHeadLine() {
		return headLine;
	}
	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}
	public String getDetailedNews() {
		return detailedNews;
	}
	public void setDetailedNews(String detailedNews) {
		this.detailedNews = detailedNews;
	}
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	} 
	
}