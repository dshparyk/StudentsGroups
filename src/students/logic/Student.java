package students.logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.text.DateFormat;
import java.util.Date;

public class Student implements Comparable<Object> {
	
	//Student fields
	private int studentId;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private char sex;
	private int groupId;
	private int educationYear;
	
	public Student(ResultSet rs) throws SQLException {
		setStudentId(rs.getInt(1));
		setFirstName(rs.getString(2));
		setLastName(rs.getString(3));
		setSex(rs.getString(4).charAt(0));
		setDateOfBirth(rs.getDate(5));
		setGroupId(rs.getInt(6));
		setEducationYear(rs.getInt(7));
	}
	
	//getters and setters for student fields
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public int getEducationYear() {
		return educationYear;
	}
	public void setEducationYear(int educationYear) {
		this.educationYear = educationYear;
	}
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	
	public String toString() {
		return lastName + " " + firstName + ", " + DateFormat.getDateInstance(DateFormat.SHORT).format(dateOfBirth)
				+ ", Group ID" + groupId + " Year:" + educationYear;
	}
	
	public int compareTo(Object obj) {
		Collator c = Collator.getInstance();
		c.setStrength(Collator.PRIMARY);
		return c.compare(this.toString(), obj.toString());
	}

}
