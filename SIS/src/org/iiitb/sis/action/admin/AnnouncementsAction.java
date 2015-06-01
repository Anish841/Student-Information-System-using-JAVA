package org.iiitb.sis.action.admin;

import java.util.Date;
import java.util.List;

import org.iiitb.sis.dao.*;
import org.iiitb.sis.dao.impl.*;
import org.iiitb.sis.model.*;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;

public class AnnouncementsAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	Announcements announcements = new Announcements();
	private int announcementsId;
	private String headLine;
	private String detailedAnnouncements;
	private String publishedDate;
	AnnouncementsDAO adao =  new AnnouncementsDAOImpl();
	private List<Announcements> announcementsList;
	private List<String> name;
	private String msg;
	
	public String fetchAnnouncements()
	{
		System.out.println("fetchAnnouncements()");
		announcementsList = adao.getAnnouncements();
		setAnnouncementsList(announcementsList);
		System.out.println("successful in fetching announcements :"+announcementsList.size());
		return SUCCESS;
	}
	
	public String addAnnouncements()
	{
		System.out.println(new Date().toString());
		announcements.setHeadLine(getHeadLine());
		announcements.setDetailedAnnouncements(getDetailedAnnouncements());
		announcements.setPublishedDate(new Date().toString());
		announcements.setAnnouncementsId(1);
		boolean res = adao.saveAnnouncements(announcements);
		if(res)
		{
			System.out.println("successful in inserting announcements");
			setMsg("true");
			return SUCCESS;
		}
		else
		{
			return ERROR;
		}
	}
	
	public String deleteAnnouncements()
	{
		System.out.println("deleteAnnouncements()");
		boolean res = adao.deleteAnnouncements(getName());
		if(res)
		{
			setMsg("true");
			return SUCCESS;
		}
		else
		{
			return ERROR;
		}
	}
	public String getHeadLine() {
		return headLine;
	}
	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	public int getAnnouncementsId() {
		return announcementsId;
	}
	public void setAnnouncementsId(int announcementsId) {
		this.announcementsId = announcementsId;
	}
	public String getDetailedAnnouncements() {
		return detailedAnnouncements;
	}
	public void setDetailedAnnouncements(String detailedAnnouncements) {
		this.detailedAnnouncements = detailedAnnouncements;
	}
	
	public List<Announcements> getAnnouncementsList() {
		return announcementsList;
	}

	public void setAnnouncementsList(List<Announcements> announcementsList) {
		this.announcementsList = announcementsList;
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
