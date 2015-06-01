package org.iiitb.sis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.sis.dao.*;
import org.iiitb.sis.model.*;
import org.iiitb.sis.util.*;

public class UserGradesDAOImpl implements UserGradesDAO {
	static Connection con = ConnectionPool.getConnection();

	@Override
	public List<Terms> getTerms(String user_id) {
		List<Terms> list = new ArrayList<Terms>();
		Terms te = new Terms();
		te.setTerm_id("All Semesters");
		te.setTerm_name("All Semesters");
		list.add(te);
		String sql = "select term_details.term_id,term_details.term_name from student_term,term_details where student_term.term_id = term_details.term_id and student_term.student_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Terms t = new Terms();
				t.setTerm_id(rs.getString("term_id"));
				t.setTerm_name(rs.getString("term_name"));
				list.add(t);
			}
		} catch (Exception e) {

		}
		return list;
	}

	public List<UserGrades> getGradesAllSemAllSub(String user_id) {
		List<UserGrades> list = new ArrayList<UserGrades>();
		String sql = "select C.term_id,C.subject_id,C.grade,C.pointer,C.term_name,subject.subject_name from subject , (select B.term_id,B.subject_id,B.grade,B.pointer,term_details.term_name from term_details , (select A.term_id,sub_enroll_details.subject_id,sub_enroll_details.grade,sub_enroll_details.pointer from sub_enroll_details, (select subject_term.subject_id,student_term.term_id from subject_term,student_term where student_term.student_id = ? and student_term.term_id = subject_term.term_id) as A where A.subject_id = sub_enroll_details.subject_id and sub_enroll_details.student_id=?) as B where term_details.term_id=B.term_id) as C where subject.subject_id=C.subject_id;";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setString(2, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserGrades grades = new UserGrades();
				grades.setTermID(rs.getString("term_id"));
				grades.setTermName(rs.getString("term_name"));
				grades.setSubjectID(rs.getString("subject_id"));
				grades.setSubjectName(rs.getString("subject_name"));
				grades.setGrade(rs.getString("grade"));
				grades.setPointer(rs.getString("pointer"));
				list.add(grades);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// ConnectionPool.freeConnection(con);
		}
		return list;
	}

	public List<UserGrades> getGradesPerSemAllSub(String user_id, String term_id) {
		List<UserGrades> list = new ArrayList<UserGrades>();
		String sql = "select C.term_id,C.subject_id,C.grade,C.pointer,C.term_name,subject.subject_name from subject , (select B.term_id,B.subject_id,B.grade,B.pointer,term_details.term_name from term_details , (select A.term_id,sub_enroll_details.subject_id,sub_enroll_details.grade,sub_enroll_details.pointer from sub_enroll_details, (select subject_term.subject_id,student_term.term_id from subject_term,student_term where student_term.student_id = ? and student_term.term_id=? and student_term.term_id = subject_term.term_id) as A where A.subject_id = sub_enroll_details.subject_id and sub_enroll_details.student_id=?) as B where term_details.term_id=B.term_id) as C where subject.subject_id=C.subject_id;";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setString(2, term_id);
			ps.setString(3, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserGrades grades = new UserGrades();
				grades.setTermID(rs.getString("term_id"));
				grades.setTermName(rs.getString("term_name"));
				grades.setSubjectID(rs.getString("subject_id"));
				grades.setSubjectName(rs.getString("subject_name"));
				grades.setGrade(rs.getString("grade"));
				grades.setPointer(rs.getString("pointer"));
				list.add(grades);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// ConnectionPool.freeConnection(con);
		}
		return list;
	}
}
