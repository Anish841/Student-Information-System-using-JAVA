package org.iiitb.sis.dao;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

public interface EnrollmentDAO {

	public ArrayList enroll(String id,Connection connection,ArrayList<String> temp_subject);
	
	
}
