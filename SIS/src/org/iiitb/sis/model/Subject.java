package org.iiitb.sis.model;

public class Subject {
	
	  private String subjectCode;
	  private String subjectName;
	  private String facultyName;
	  private String term;
	  private String enrolled;
	  private String grade;
	  private String point;
	  private String syllabus_text;
	  private String syllabus_type;

	  public Subject(){
		  
	  }
	 public Subject(String subjectCode, String subjectName,
	      String facultyName, String term, String enrolled, String grade,String point) {
	    this.subjectCode = subjectCode;
	    this.subjectName = subjectName;
	    this.facultyName = facultyName;
	    this.term = term;
	    this.enrolled = enrolled;
	    this.grade = grade;
	    this.point = point;
	  }

	  
	  public String getEnrolled() {
	    return enrolled;
	  }

	  public String getSubjectCode() {
	    return subjectCode;
	  }

	  public void setSubjectCode(String subjectCode) {
	    this.subjectCode = subjectCode;
	  }

	  public String getFacultyName() {
	    return facultyName;
	  }

	  public void setFacultyName(String facultyName) {
	    this.facultyName = facultyName;
	  }

	  
	  public void setEnrolled(String enrolled) {
	    this.enrolled = enrolled;
	  }

	  public String getGrade() {
	    return grade;
	  }

	  public void setGrade(String grade) {
	    this.grade = grade;
	  }

	  public String getSubjectName() {
	    return subjectName;
	  }

	  public void setSubjectName(String subjectName) {
	    this.subjectName = subjectName;
	  }

	  @Override
	  public String toString() {
	    return this.subjectName;
	  }


	public String getTerm() {
		return term;
	}


	public void setTerm(String term) {
		this.term = term;
	}


	public String getPoint() {
		return point;
	}


	public void setPoint(String point) {
		this.point = point;
	}
	public String getSyllabus_text() {
		return syllabus_text;
	}
	public void setSyllabus_text(String syllabus_text) {
		this.syllabus_text = syllabus_text;
	}
	public String getSyllabus_type() {
		return syllabus_type;
	}
	public void setSyllabus_type(String syllabus_type) {
		this.syllabus_type = syllabus_type;
	}

}
