package org.iiitb.sis.dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.sis.model.Interest;

public interface EditProfileDAO {
	
	public ArrayList<Interest> getAllInterest();
	public boolean saveProfileChange(String email,String password,String userId,List<String> interest,String mobno);
	public boolean updatePhoto(String userId,InputStream pic);
}
