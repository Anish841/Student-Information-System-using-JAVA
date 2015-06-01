package org.iiitb.sis.dao;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;

public interface AddStudentDAO {

	public boolean addstudent(Connection connection, String id, String name, String pwd, String email,
			String mob, String add, String dob, String gender,String usertype,String termId);
}
