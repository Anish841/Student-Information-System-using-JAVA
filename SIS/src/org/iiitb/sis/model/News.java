package org.iiitb.sis.model;

public class News {
		private int newsId;
		private String headLine;
		private String detailedNews;
		private String publishedDate;
		
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
}
