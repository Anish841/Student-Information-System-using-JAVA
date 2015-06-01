package org.iiitb.sis.dao.impl;

import java.util.ArrayList;

import org.iiitb.sis.dao.NotificationDAO;
import org.iiitb.sis.model.Notification;
import org.iiitb.sis.util.MongoDBConnection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class NotificationDAOImpl implements NotificationDAO{

	@Override
	public ArrayList<Notification> getAllNotification(String userId) {
		DB db=MongoDBConnection.getConnection();
		DBCollection coll=db.getCollection("notifications");
		
		BasicDBObject userNotification=new BasicDBObject();
		userNotification.put("userId", userId);
		userNotification.put("seen", "false");
		 
		DBCursor dc=coll.find(userNotification);
		
		ArrayList<Notification> al=new ArrayList<Notification>();
		Notification n=null;
		int len=0;
		while(dc.hasNext()){
			DBObject doc=dc.next();
			len++;
			n=parseJson(doc);
			n.setLen(len);
			al.add(n);
		}
		return al;
	}
	
	public boolean notifyUser(String userId,String message,String notiType,String url){
		DB db=MongoDBConnection.getConnection();
		DBCollection coll=db.getCollection("notifications");
		
		BasicDBObject userNotification=new BasicDBObject();
		userNotification.append("userId", userId).append("noti_name", message)
		.append("noti_detail", message).append("seen", "false").append("noti_type", notiType)
		.append("url", url);
		 
		coll.insert(userNotification);
		return true;
	}
	private Notification parseJson(DBObject ob){
		Notification n=new Notification();
		System.out.println((String)ob.get("noti_name"));
		n.setNotiName((String)ob.get("noti_name"));
		n.setNotiDetail((String)ob.get("noti_detail"));
		n.setSeen((String)ob.get("seen"));
		n.setNotiType((String)ob.get("noti_type"));
		n.setUrl((String)ob.get("url"));
		return n;
	}
	
	
	public static void main(String args[]){

		NotificationDAOImpl n=new NotificationDAOImpl();
//		n.notifyUser("us123","Some User Pinged You.");
		
		ArrayList a=n.getAllNotification("us123");
		Notification a1=(Notification)a.get(a.size()-1);
		System.out.println(a1.getLen());
	}
}
