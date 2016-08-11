package students.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import students.logic.Group;
import students.logic.ManagementSystem;
import students.logic.Student;

public class StudentsFrame extends JFrame{

	//creating groupList, studentList and Year
	ManagementSystem ms = ManagementSystem.getInstance();
	private JList<Object> grpList;
	private JList<Object> stdList;
	private JSpinner spYear;
	
	public StudentsFrame() {
		
		//Setup layout for client part of form
		getContentPane().setLayout(new BorderLayout());
		
		//upper panel with field for year input
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.add(new JLabel("Year of education:"));
		
		//adding Spinner
		SpinnerModel sm = new SpinnerNumberModel(2016, 1900, 2100, 1);
		spYear = new JSpinner(sm);
		top.add(spYear);
		
		//Creating bottom panel
		JPanel bot = new JPanel();
		bot.setLayout(new BorderLayout());
		
		//Creating left panel for groups list output
		JPanel left = new JPanel();
		left.setLayout(new BorderLayout());
		left.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		//getting Vector of groups
		Vector<Group> gr = new Vector<Group>(ms.getGroups());
		left.add(new JLabel("Groups:"), BorderLayout.NORTH);
		//creating list add adding it to scroll panel
		grpList = new JList<Object>(gr);
		left.add(new JScrollPane(grpList), BorderLayout.CENTER);
		
		//right panel for students list output
		JPanel right = new JPanel();
		right.setLayout(new BorderLayout());
		right.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		//getting students list
		Vector<Student> st = new Vector<Student>(ms.getAllStudents());
		right.add(new JLabel("Students:"), BorderLayout.NORTH);
		//creating list add adding it to scroll panel
		stdList = new JList<Object>(st);
		right.add(new JScrollPane(stdList), BorderLayout.CENTER);
		
		//adding panel with groups and students lists to bottom panel
		bot.add(left, BorderLayout.WEST);
		bot.add(right, BorderLayout.CENTER);
		
		//adding top and bot panels into form
		getContentPane().add(top, BorderLayout.NORTH);
		getContentPane().add(bot, BorderLayout.CENTER);
		
		setBounds(100, 100, 600, 400);
	}
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				StudentsFrame sf = new StudentsFrame();
				sf.setDefaultCloseOperation(EXIT_ON_CLOSE);
				sf.setVisible(true);
			}
		});
	}
//	public static void main(String args[]) {
//		StudentsFrame sf = new StudentsFrame();
//		sf.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		sf.setVisible(true);
//	}
	
}