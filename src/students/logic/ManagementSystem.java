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

}








