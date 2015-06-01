package org.iiitb.sis.util;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoDBConnection {
	public static DB getConnection(){
		MongoClient con=null;
		 DB db = null;
		try {
			con = new MongoClient( "localhost" , 27017 );
			db=con.getDB( "StudentDB" );
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return db;
	}
	public static void freeConnection(MongoClient con){
		if(con!=null){
			con.close();
		}
	}
}
