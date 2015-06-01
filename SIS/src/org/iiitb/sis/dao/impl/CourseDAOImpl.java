package org.iiitb.sis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.sis.dao.CourseDAO;
import org.iiitb.sis.model.Student;
import org.iiitb.sis.model.Subject;
import org.iiitb.sis.util.ConnectionPool;

public class CourseDAOImpl implements CourseDAO {

	private ArrayList<String> subjectList;
	private ArrayList<String> programmeList;
	private ArrayList<String> facultyList;
	private ArrayList<String> term_id_list;
	private static final String ALL_COURSES_QUERY = "SELECT STUDENT_SUBJECT.student_id AS student_id , ALL_SUBJECTS.subject_id AS subject_id, subject_name, faculty_name , term_name, STUDENT_SUBJECT.grade AS grade "
			+ ",STUDENT_SUBJECT.student_id FROM "
			+ "(SELECT s.subject_name,s.subject_id AS subject_id, u.name AS faculty_name,td.term_name AS term_name "
			+ "FROM subject s LEFT OUTER JOIN faculty_subject fsub ON s.subject_id = fsub.subject_id "
			+ "LEFT OUTER JOIN user u ON u.user_id = fsub.faculty_id "
			+ "LEFT OUTER JOIN subject_term st ON fsub.subject_id = st.subject_id "
			+ "LEFT OUTER JOIN term_details td ON st.term_id = td.term_id)ALL_SUBJECTS "
			+ " LEFT OUTER JOIN "
			+ " (SELECT s.subject_id,stt.student_id AS student_id,sed.grade "
			+ "FROM subject s LEFT OUTER JOIN faculty_subject fsub ON s.subject_id = fsub.subject_id "
			+ "LEFT OUTER JOIN user u ON u.user_id = fsub.faculty_id "
			+ "LEFT OUTER JOIN subject_term st ON fsub.subject_id = st.subject_id "
			+ "LEFT OUTER JOIN term_details td ON st.term_id = td.term_id "
			+ "LEFT OUTER JOIN student_term stt ON st.term_id = stt.term_id "
			+ "LEFT OUTER JOIN sub_enroll_details sed ON st.subject_id = sed.subject_id "
			+ "AND stt.student_id = sed.student_id WHERE stt.student_id = 'MT2014063' "
			+ ")STUDENT_SUBJECT ON ALL_SUBJECTS.subject_id = STUDENT_SUBJECT.subject_id "
			+ "ORDER BY ALL_SUBJECTS.subject_id ";

	private String ENROLLED_COURSE_QUERY = "select sed.subject_id, s.subject_name, f.faculty_id, sed.grade from "
			+ "StudentDB.sub_enroll_details sed "
			+ "inner join StudentDB.subject s on sed.subject_id = s.subject_id "
			+ "inner join StudentDB.faculty_subject fs on fs.subject_id = s.subject_id "
			+ "inner join StudentDB.faculty f on f.faculty_id = fs.faculty_id "
			+ "where sed.student_id = ?";

	@Override
	public ArrayList<Subject> getSubjectList(String termId) {
		Connection connection = ConnectionPool.getConnection();
		ArrayList<Subject> subjectList = new ArrayList<Subject>();
		Subject s = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("select * from subject where subject_id In(select subject_id from subject_term where term_id=?) and subject_id NOT IN(select subject_id from faculty_subject)");
			ps.setString(1, termId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = new Subject();
				s.setSubjectCode(rs.getString("subject_id"));
				s.setSubjectName(rs.getString("subject_id")+"-"+rs.getString("subject_name"));
				subjectList.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				ConnectionPool.freeConnection(connection);
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return subjectList;
	}

	@Override
	public ArrayList<String> getProgrammeList() {
		Connection connection = ConnectionPool.getConnection();
		programmeList = new ArrayList<String>();
		PreparedStatement ps = null;
		try {
			System.out.println("programme");
			ps = connection
					.prepareStatement("select distinct programme from term_details");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("programme"));
				programmeList.add(rs.getString("programme"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				ConnectionPool.freeConnection(connection);
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return programmeList;
	}

	@Override
	public ArrayList<String> getTermList(String prog) {
		Connection connection = ConnectionPool.getConnection();
		ArrayList<String> termList = new ArrayList<String>();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("select distinct(term_name) from term_details where programme='"
							+ prog + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				termList.add(rs.getString("term_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				ConnectionPool.freeConnection(connection);
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return termList;
	}

	
	@Override
	public ArrayList<Student> getFacultyList() {
		ArrayList<Student> facultyList = new ArrayList<Student>();
		Connection connection = ConnectionPool.getConnection();
		Student s=null;
		PreparedStatement ps = null;
		try {
			// System.out.println("faculty");
			ps = connection
					.prepareStatement("select user_id,name from user WHERE user_type='F'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s=new Student();
				s.setStudent_id(rs.getString("user_id"));
				s.setName(rs.getString("name"));
				facultyList.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				ConnectionPool.freeConnection(connection);	
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return facultyList;
	}

	@Override
	public boolean addCourse(String subjectId,List<String> facultyId) {
		PreparedStatement ps = null;
		try {
					int n=0;
					Connection connection = ConnectionPool.getConnection();
					int i=0;
					System.out.println(subjectId+" "+facultyId);
					while(facultyId!=null && facultyId.size()>0){
						ps = connection.prepareStatement("insert into faculty_subject(faculty_id,subject_id) values(?,?)");
						ps.setString(1, facultyId.get(i++));
						ps.setString(2, subjectId);
						n=ps.executeUpdate();
					}
					if(n>0)
						return true;
					else
						return false;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if (null != ps) {
					try {
						ps.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		return false;
	}

	private void createSubjectInfoListFromResultSet(ResultSet rs,
			List<Subject> subjectInfoList, String point) throws SQLException {
		while (rs.next()) {
			String subjectCode = rs.getString("subject_id");
			String subjectName = rs.getString("subject_name");
			String facultyNameVal = rs.getString("faculty_name");
			String facultyName = (null == facultyNameVal) ? "NA"
					: facultyNameVal;
			String semesterVal = rs.getString("term_name");
			String semester = (null == semesterVal) ? "NA" : semesterVal;
			String enrolledVal = rs.getString("student_id");
			String enrolled = (null == enrolledVal) ? "N" : "Y";
			String gradeVal = rs.getString("grade");
			String grade = (null == gradeVal) ? "NA" : gradeVal;
			String point1 = point;
			Subject subjectInfo = new Subject(subjectCode, subjectName,
					facultyName, semester, enrolled, grade, point1);
			subjectInfoList.add(subjectInfo);
		}
	}

	@Override
	public ArrayList<Subject> getAllCourses(Connection connection, String string) {
		ArrayList<Subject> subjectInfoList = null;
		PreparedStatement ps = null;
		try {
			subjectInfoList = new ArrayList<Subject>();
			ps = connection.prepareStatement(ALL_COURSES_QUERY);
			// ps.setInt(index, userId);
			ResultSet rs = ps.executeQuery();
			createSubjectInfoListFromResultSet(rs, subjectInfoList,
					"show all courses");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return subjectInfoList;
	}

	@Override
	public ArrayList<Subject> getEnrolledCourses(Connection connection,
			String string) {
		ArrayList<Subject> subjectInfoList = null;
		PreparedStatement ps = null;
		try {
			subjectInfoList = new ArrayList<Subject>();
			ps = connection.prepareStatement(ENROLLED_COURSE_QUERY);
			// ps.setString(index, student);
			ResultSet rs = ps.executeQuery();
			createSubjectInfoListFromResultSet(rs, subjectInfoList,
					"Show Enrolled Courses");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return subjectInfoList;
	}

	@Override
	public String getSyllabus(Connection connection, String subjectCode) {
		String syllabus = null;
		PreparedStatement ps = null;
		try {
			ps = connection
					.prepareStatement("select syllabus FROM subject s WHERE s.subject_id = ? ");
			int index = 1;
			ps.setString(index, subjectCode);
			ResultSet rs = ps.executeQuery();
			syllabus = rs.getString("syllabus");

			// createSyllabusInfoListFromResultSet(rs, syllabusInfoList);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return syllabus;

	}

	public ArrayList<Subject> getEnrolledCourses(String userId) {
		ArrayList<Subject> subjectInfoList = null;
		Connection conn = ConnectionPool.getConnection();
		PreparedStatement ps = null;
		try {
			subjectInfoList = new ArrayList<Subject>();
			ps = conn.prepareStatement(ENROLLED_COURSE_QUERY);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Subject s = new Subject();
				s.setSubjectCode(rs.getString("subject_id"));
				s.setGrade(rs.getString("grade"));
				s.setSubjectName(rs.getString("subject_name"));
				s.setFacultyName(rs.getString("faculty_id"));
				subjectInfoList.add(s);
				System.out.println("from getEnrolledCourses " + s.getGrade()
						+ s.getFacultyName() + s.getSubjectName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
					ConnectionPool.freeConnection(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("@@@@@@@@@@ " + subjectInfoList.size());
		return subjectInfoList;
	}

}
