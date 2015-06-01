package org.iiitb.sis.dao;

import java.util.ArrayList;

import org.iiitb.sis.model.User;

public interface FriendsDAO {

	public ArrayList<User> getAllFriends(String userid);

	public String getLastLogin(String userid);

	public User getSingleUser(String userid);

	public User SearchUser(String friendid,String SessionId); 
	
	public boolean addFriend(String id1,String id2);
	
	public boolean removeFriend(String id1,String id2);
}
