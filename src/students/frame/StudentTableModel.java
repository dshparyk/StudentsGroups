package students.frame;

import java.text.DateFormat;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import students.logic.Student;

public class StudentTableModel extends AbstractTableModel {

	private Vector students;
	
	public StudentTableModel (Vector students) {
		this.students = students;
	}
	
	//rows are equals to the number of records
	public int getRowCount() {
		if (students != null) {
			return students.size();
		}
		return 0;
	}
	
	//we have 3 columns - LastName, FirstName, BirthDate
	public int getColumnCount() {
		return 3;
	}
	
	public String getColumnName(int column) {
		String[] colNames = {"LastName", "FirstName", "Date"};
		return colNames[column];
	}

	//get value for required row or column
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (students != null) {
			//get student from vector
			Student st = (Student) students.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return st.getLastName();
			case 1:
				return st.getFirstName();
			case 2:
				return DateFormat.getDateInstance(DateFormat.SHORT).format(
						st.getDateOfBirth());
			}
		}
		return null;
	}
	
	//adding method that returning student by row number
	public Student getStudent(int rowIndex) {
		if (students != null) {
			if (rowIndex < students.size() && rowIndex >= 0) {
				return (Student) students.get(rowIndex);
			}
		}
		return null;
	}
}