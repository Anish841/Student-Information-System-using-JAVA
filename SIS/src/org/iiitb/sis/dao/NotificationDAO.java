package org.iiitb.sis.dao;

import java.util.ArrayList;

import org.iiitb.sis.model.Notification;

public interface NotificationDAO {

	public ArrayList<Notification> getAllNotification(String userId);
	public boolean notifyUser(String userId,String message,String notiType,String url);
}
