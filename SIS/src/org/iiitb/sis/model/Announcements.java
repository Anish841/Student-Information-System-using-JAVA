package org.iiitb.sis.model;

public class Announcements {
		private int announcementsId;
		private String headLine;
		private String detailedAnnouncements;
		private String publishedDate;
		public int getAnnouncementsId() {
			return announcementsId;
		}
		public void setAnnouncementsId(int announcementsId) {
			this.announcementsId = announcementsId;
		}
		public String getHeadLine() {
			return headLine;
		}
		public void setHeadLine(String headLine) {
			this.headLine = headLine;
		}
		public String getDetailedAnnouncements() {
			return detailedAnnouncements;
		}
		public void setDetailedAnnouncements(String detailedAnnouncements) {
			this.detailedAnnouncements = detailedAnnouncements;
		}
		public String getPublishedDate() {
			return publishedDate;
		}
		public void setPublishedDate(String publishedDate) {
			this.publishedDate = publishedDate;
		}
		
}
