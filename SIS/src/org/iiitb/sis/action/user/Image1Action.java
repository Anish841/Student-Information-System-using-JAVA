package org.iiitb.sis.action.user;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.iiitb.sis.dao.LoginDAO;
import org.iiitb.sis.dao.impl.LoginDAOimpl;
import org.iiitb.sis.util.ConnectionPool;


public class Image1Action {
	private LoginDAO tDao = new LoginDAOimpl();
	  private String userId;
	 
	  public String execute() throws SQLException, IOException {
	    Connection connection = ConnectionPool.getConnection();
	     
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setContentType("image/jpeg");
	    InputStream in = tDao.displayphoto(userId);
	    OutputStream out = response.getOutputStream();
	    byte[] buffer = new byte[1024];
	    int len;
	    while ((len = in.read(buffer)) != -1) {
	      out.write(buffer, 0, len);
	    }
	    ConnectionPool.freeConnection(connection);
	    return "none";
	  }

	   public String getUserId() {
	    return userId;
	  }

	  public void setUserId(String userId) {
	    this.userId = userId;
	  }
}
