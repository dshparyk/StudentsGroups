package students.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class ManagementSystem {

	private List<Group> groups;
	private Collection<Student> students;
	
	//static field for Singleton
	private static ManagementSystem instance;
	
	private ManagementSystem() {
		loadGroups();
		loadStudents();
	}
	
	//getInstance() checks if instance is already initialized
	public static synchronized ManagementSystem getInstance() {
		if (instance == null) {
			instance = new ManagementSystem();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		
		ManagementSystem ms = ManagementSystem.getInstance();
		
		//print full list of groups
		System.out.println("Full list of Groups");
		System.out.println("-------------------");
		List<Group> allGroups = ms.getGroups();
		for(Group g : allGroups) {
			System.out.println(g);
		}
		System.out.println();
		
		//print full list of students
		System.out.println("Full list of Students");
		System.out.println("---------------------");
		Collection<Student> allStudents = ms.getAllStudents();
		for(Student s : allStudents) {
			System.out.println(s);
		}
		System.out.println();
		
		//print students list by group
		System.out.println("Students list by Group");
		System.out.println("----------------------");
		List<Group> groups = ms.getGroups();
		for(Group g : groups) {
			System.out.println("---> Group:" + g.getNameGroup());
			Collection<Student> students = ms.getStudentsFromGroup(g, 2016);
			for (Student s : students) {
				System.out.println(s);
			}
		}
		System.out.println();
		
		//create new student and add it into list
		Student s = new Student();
		s.setStudentId(5);
		s.setFirstName("Mike");
		s.setLastName("Plank");
		s.setSex('M');
		Calendar c = Calendar.getInstance();
		c.set(2001, 8, 18);
		s.setDateOfBirth(c.getTime());
		s.setGroupId(1);
		s.setEducationYear(2016);
		System.out.println("Adding student:" + s);
		System.out.println("---------------");
		ms.insertStudent(s);
		System.out.println("Full list of students after adding");
		allStudents = ms.getAllStudents();
		for (Student st : allStudents) {
			System.out.println(st);
		}
		System.out.println();
		
		//updating student's last name
		s = new Student();
		s.setStudentId(5);
		s.setFirstName("Mike");
		s.setLastName("MacPlank");
		s.setSex('M');
		c = Calendar.getInstance();
		c.set(2001, 8, 18);
		s.setDateOfBirth(c.getTime());
		s.setGroupId(1);
		s.setEducationYear(2016);
		System.out.println("Updating student:" + s);
		System.out.println("-----------------");
		ms.updateStudent(s);
		System.out.println("Full list of students after adding");
		allStudents = ms.getAllStudents();
		for (Student st : allStudents) {
			System.out.println(st);
		}
		System.out.println();
		
		//Remove student
		System.out.println("Removing student:" + s);
		System.out.println("-----------------");
		ms.deleteStudent(s);
		System.out.println("Full list of students after removing");
		allStudents = ms.getAllStudents();
		for (Student st : allStudents) {
			System.out.println(st);
		}
		System.out.println();
		
		//moving students from one group to another
		Group g1 = groups.get(0);
		Group g2 = groups.get(1);
		System.out.println("Moving students from 1st to 2nd group");
		System.out.println("-------------------------------------");
		ms.moveStudentsToGroup(g1, 2016, g2, 2017);
		System.out.println("Full list of students after moving");
		allStudents = ms.getAllStudents();
		for (Student st : allStudents) {
			System.out.println(st);
		}
		System.out.println();
		
		//removing students from group
		System.out.println("Removing students from group:" + g2 + " in 2016 year");
		System.out.println("-----------------------------");
		ms.removeStudentsFromGroup(g2, 2016);
		System.out.println("Full list of students after removing");
		allStudents = ms.getAllStudents();
		for (Student st : allStudents) {
			System.out.println(st);
		}
		System.out.println();
	}
	
	public void loadGroups() {
		if (groups == null) {
			groups = new ArrayList<Group>();
		} else {
			groups.clear();
		}
		Group g = null;
		
		g = new Group();
		g.setGroupId(1);
		g.setNameGroup("First");
		g.setCurator("Walter White");
		g.setSpeciality("Chemistry");
		groups.add(g);
		
		g = new Group();
		g.setGroupId(2);
		g.setNameGroup("Second");
		g.setCurator("Jesse Pinkman");
		g.setSpeciality("Medicine");
		groups.add(g);
	}
	
	public void loadStudents() {
		if (students == null) {
			students = new TreeSet<Student>();
		} else {
			students.clear();
		}
		
		Student s = null;
		Calendar c = Calendar.getInstance();
		
		s = new Student();
		s.setStudentId(1);
		s.setFirstName("John");
		s.setLastName("Smith");
		s.setSex('M');
		c.set(2000, 2, 20);
		s.setDateOfBirth(c.getTime());
		s.setGroupId(2);
		s.setEducationYear(2016);
		students.add(s);
		
		s = new Student();
		s.setStudentId(2);
		s.setFirstName("Liza");
		s.setLastName("Brown");
		s.setSex('F');
		c.set(2000, 3, 30);
		s.setDateOfBirth(c.getTime());
		s.setGroupId(2);
		s.setEducationYear(2016);
		students.add(s);
		
		s = new Student();
		s.setStudentId(3);
		s.setFirstName("Jim");
		s.setLastName("Black");
		s.setSex('M');
		c.set(2000, 4, 14);
		s.setDateOfBirth(c.getTime());
		s.setGroupId(1);
		s.setEducationYear(2016);
		students.add(s);
		
		s = new Student();
		s.setStudentId(4);
		s.setFirstName("Megan");
		s.setLastName("Dart");
		s.setSex('F');
		c.set(2000, 5, 15);
		s.setDateOfBirth(c.getTime());
		s.setGroupId(1);
		s.setEducationYear(2016);
		students.add(s);	
	}
	
	public List<Group> getGroups() {
		return groups;
	}
	
	private Collection<Student> getAllStudents() {
		return students;
	}
	
	public Collection<Student> getStudentsFromGroup(Group group, int year) {
		Collection<Student> l = new TreeSet<Student>();
		for (Student s : students) {
			if (s.getGroupId() == group.getGroupId() && s.getEducationYear() == year) {
				l.add(s);
			}
		}
		return l;
	}
	
	public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) {
		for (Student si : students) {
            if (si.getGroupId() == oldGroup.getGroupId() && si.getEducationYear() == oldYear) {
                si.setGroupId(newGroup.getGroupId());
                si.setEducationYear(newYear);
            }
        }
	}
	
	public void removeStudentsFromGroup(Group group, int year) {
		Collection<Student> tmp = new TreeSet<Student>();
        for (Student si : students) {
            if (si.getGroupId() != group.getGroupId() || si.getEducationYear() != year) {
                tmp.add(si);
            }
        }
        students = tmp;
	}
	
	public void insertStudent(Student student) {
		students.add(student);
	}
	
	public void updateStudent(Student student) {
		Student updStudent = null;
        for (Student s : students) {
            if (s.getStudentId() == student.getStudentId()) {
                updStudent = s;
                break;
            }
        }
        updStudent.setFirstName(student.getFirstName());
        updStudent.setLastName(student.getLastName());
        updStudent.setSex(student.getSex());
        updStudent.setDateOfBirth(student.getDateOfBirth());
        updStudent.setGroupId(student.getGroupId());
        updStudent.setEducationYear(student.getEducationYear());
	}
	
	public void deleteStudent(Student student) {
        Student delStudent = null;
        for (Student si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                delStudent = si;
                break;
            }
        }
        students.remove(delStudent);
    }

}








