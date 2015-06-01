package org.iiitb.sis.dao;

import java.io.InputStream;

import org.iiitb.sis.model.User;

public interface LoginDAO {

	public User isValidUser(User user);
	public boolean updateLogoutTime(String userId,String date);
	public String getTermDetails(String userId);
	public String getInterest(String userId);
	public InputStream displayphoto(String userId);
}
