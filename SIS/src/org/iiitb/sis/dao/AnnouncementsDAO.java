package org.iiitb.sis.dao;

import java.util.List;

import org.iiitb.sis.model.Announcements;

public interface AnnouncementsDAO {
	
	public List<Announcements> getAnnouncements();
	public boolean saveAnnouncements(Announcements announcements);
	public boolean deleteAnnouncements(List<String> list);	
}
