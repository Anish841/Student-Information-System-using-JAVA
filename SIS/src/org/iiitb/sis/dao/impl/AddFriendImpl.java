package org.iiitb.sis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.iiitb.sis.model.Student;
import org.iiitb.sis.util.ConnectionPool;

public class AddFriendImpl {

	public String getUserBirthPlace(String id){
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement preStmt;
		ResultSet rs=null;
		ArrayList<Student> studentList=new ArrayList<Student>();
		ArrayList<Student> studentListFinal=new ArrayList<Student>();
		String bp=null;
		try {
			preStmt = conn.prepareStatement("select BirthPlace from user where user_id=?");
			preStmt.setString(1,id);
			rs = preStmt.executeQuery();
			int count=1;
			
			while(rs.next()){
				bp=rs.getString("BirthPlace");
			}
			if(bp!=null){
				bp=bp.substring(0,bp.length()-3);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		return bp;
	}
	public ArrayList<Student> getStudents(String birthPlace,String userId){
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement preStmt;
		ResultSet rs=null;
		ArrayList<String> uid=new ArrayList<String>();
		ArrayList<String> uid1=new ArrayList<String>();
		ArrayList<Student> studentList=new ArrayList<Student>();
		ArrayList<Student> studentListFinal=new ArrayList<Student>();
		Student s=null;
		try {
			preStmt = conn.prepareStatement("select * from user where BirthPlace like ?");
			preStmt.setString(1,birthPlace+"%");
			rs = preStmt.executeQuery();
			int count=1;
			while(rs.next()){
				s=new Student();
				count++;
				uid.add(rs.getString("user_id"));
			}
			System.out.println("check1==>"+count);
			int count2=1;
			if(count>1){
				for(int i=0;i<count-1;i++){
					String id=uid.get(i);
					preStmt = conn.prepareStatement("select * from student_friends where student_id1=? and student_id2=?");
					preStmt.setString(1,id);
					preStmt.setString(2, userId);
					rs=preStmt.executeQuery();
					boolean f=false;
					if(rs.next()){
						f=true;
					}
					if(!f){
						uid1.add(id);
					}
										
				}
			}
			System.out.println("check2==>"+uid1.size());
			if(uid1!=null && uid1.size()>0){
					int len2=uid1.size();
					for(int i=0;i<len2;i++){
						preStmt = conn.prepareStatement("select * from user where user_id =?");
						preStmt.setString(1,uid1.get(i));
						System.out.println(uid1.get(i));
						rs = preStmt.executeQuery();
						count=1;
						if(rs.next()){
							s=new Student();
							
							if(!userId.equalsIgnoreCase(rs.getString("user_id"))){
								count++;
								s.setStudent_id(rs.getString("user_id"));
								s.setName(rs.getString("name"));
								studentList.add(s);
							}
							
						}
					}					
					System.out.println("student===>"+studentList.size());
					if(studentList!=null && studentList.size()>0){
						count2=1;
						int len1=studentList.size();
						for(int i=0;i<len1;i++){
							Student s1=studentList.get(i);
							preStmt = conn.prepareStatement("select * from term_details where term_id In(select term_id from student_term where student_id=?)");
							preStmt.setString(1, s1.getStudent_id());
							rs = preStmt.executeQuery();
							Student sf=null;
							while(rs.next()){
								sf=new Student();
								sf.setSno(String.valueOf(count2++));
								sf.setStudent_id(s1.getStudent_id());
								sf.setTerm(rs.getString("term_name"));
								sf.setProg(rs.getString("programme"));
								sf.setName(s1.getName());
								studentListFinal.add(sf);
							}
						}
					}
					System.out.println("check3");
				}
			}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		return studentListFinal;
}}