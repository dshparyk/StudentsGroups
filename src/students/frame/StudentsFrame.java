package students.frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import students.logic.Group;
import students.logic.ManagementSystem;
import students.logic.Student;

public class StudentsFrame extends JFrame implements ActionListener, ListSelectionListener, ChangeListener{
	
	private static final String MOVE_GR = "moveGroup";
	private static final String CLEAR_GR = "clearGroup";
	private static final String INSERT_ST = "insertStudent";
	private static final String UPDATE_ST = "updateStudent";
	private static final String DELETE_ST = "deleteStudent";
	private static final String ALL_STUDENTS = "allStudent";
	private ManagementSystem ms = null;
	private JList<Object> grpList;
	private JTable stdList;
	private JSpinner spYear;
	
	public StudentsFrame() throws Exception{
		
		//Setup layout for client part of form
		getContentPane().setLayout(new BorderLayout());
		
		//Creating Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Reports");
		JMenuItem menuItem = new JMenuItem("All Students");
		menuItem.setName(ALL_STUDENTS);
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		//Creating upper panel with field for year input
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.add(new JLabel("Year of education:"));
		
		//Adding Spinner
		SpinnerModel sm = new SpinnerNumberModel(2016, 1900, 2100, 1);
		spYear = new JSpinner(sm);
		spYear.addChangeListener(this);
		top.add(spYear);
		
		//Creating bottom panel
		JPanel bot = new JPanel();
		bot.setLayout(new BorderLayout());
		
		//Creating left panel for groups list output
		GroupPanel left = new GroupPanel();
		left.setLayout(new BorderLayout());
		left.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		//Getting connection on DB
		ms = ManagementSystem.getInstance();
		//Getting groups list
		Vector<Group> gr = new Vector<Group>(ms.getGroups());
		left.add(new JLabel("Groups:"), BorderLayout.NORTH);
		//creating list, adding it to scroll panel
		grpList = new JList<Object>(gr);
		grpList.addListSelectionListener(this);
		grpList.setSelectedIndex(0);
		left.add(new JScrollPane(grpList), BorderLayout.CENTER);
		
		//Creating Buttons for groups
		JButton btnMvGr = new JButton("Move");
		btnMvGr.setName(MOVE_GR);
		JButton btnClGr = new JButton("Clear");
		btnClGr.setName(CLEAR_GR);
		btnMvGr.addActionListener(this);
		btnClGr.addActionListener(this);
		
		//creating panel for buttons
		JPanel pnlBtnGr = new JPanel();
		pnlBtnGr.setLayout(new GridLayout(1, 2));
		pnlBtnGr.add(btnMvGr);
		pnlBtnGr.add(btnClGr);
		left.add(pnlBtnGr, BorderLayout.SOUTH);
		
		//right panel for students list output
		JPanel right = new JPanel();
		right.setLayout(new BorderLayout());
		right.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		//adding students label
		right.add(new JLabel("Students:"), BorderLayout.NORTH);
		//creating table with scrolling panel
		stdList = new JTable(1, 3);
		right.add(new JScrollPane(stdList), BorderLayout.CENTER);
		
		//creating Buttons for Students
		JButton btnAddSt = new JButton("Add");
		btnAddSt.setName(INSERT_ST);
		btnAddSt.addActionListener(this);
		JButton btnUpdSt = new JButton("Update");
		btnUpdSt.setName(UPDATE_ST);
		btnUpdSt.addActionListener(this);
		JButton btnDelSt = new JButton("Delete");
		btnDelSt.setName(DELETE_ST);
		btnDelSt.addActionListener(this);
		
		//Creating Panel for Buttons
		JPanel pnlBtnSt = new JPanel();
		pnlBtnSt.setLayout(new GridLayout(1, 3));
		pnlBtnSt.add(btnAddSt);
		pnlBtnSt.add(btnUpdSt);
		pnlBtnSt.add(btnDelSt);
		right.add(pnlBtnSt, BorderLayout.SOUTH);
			
		//adding panel with groups and students lists to bottom panel
		bot.add(left, BorderLayout.WEST);
		bot.add(right, BorderLayout.CENTER);
		
		//adding top and bottom panels into form
		getContentPane().add(top, BorderLayout.NORTH);
		getContentPane().add(bot, BorderLayout.CENTER);
		
		setBounds(100, 100, 700, 500);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof Component) {
			Component c = (Component) e.getSource();
			if (c.getName().equals(MOVE_GR)) {
				moveGroup();
			}
			if (c.getName().equals(CLEAR_GR)) {
				clearGroup();
			}
			if (c.getName().equals(ALL_STUDENTS)) {
				showAllStudents();
			}
			if (c.getName().equals(INSERT_ST)) {
				insertStudent();
			}
			if (c.getName().equals(UPDATE_ST)) {
				updateStudent();
			}
			if (c.getName().equals(DELETE_ST)) {
				deleteStudent();
			}
		}
	}
	
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			reloadStudents();
		}
	}
	
	public void stateChanged(ChangeEvent e) {
		reloadStudents();
	}
	
	public void reloadStudents() {
		Thread t = new Thread() {
			public void run() {
				if (stdList != null) {
					Group g = (Group) grpList.getSelectedValue();
					//getting year from spinner
					int y = ((SpinnerNumberModel) spYear.getModel()).getNumber().intValue();
					try {
						//getting students list
						Collection<Student> s = ms.getStudentsFromGroup(g, y);
						//setting model for table with new data
						stdList.setModel(new StudentTableModel(new Vector<Student>(s)));
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(StudentsFrame.this, e.getMessage());
					}
				}
			}
		};
		t.start();
	}
	
	public void moveGroup() {
		JOptionPane.showMessageDialog(this, "moveGroup");
	}
	
	public void clearGroup() {
		Thread t = new Thread() {
			public void run() {
				if (grpList.getSelectedValue() != null) {
					if (JOptionPane.showConfirmDialog(StudentsFrame.this,
							"Do you want to delete students from group?", "Delete students",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						Group g = (Group) grpList.getSelectedValue();
						int y = ((SpinnerNumberModel) spYear.getModel()).getNumber().intValue();
						try {
							ms.removeStudentsFromGroup(g, y);
							reloadStudents();
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(StudentsFrame.this, e.getMessage());
						}
					}
				}
			}
		};
		t.start();
	}
	
	public void insertStudent() {
		JOptionPane.showMessageDialog(this, "insertStudent");
	}
	
	public void updateStudent() {
		JOptionPane.showMessageDialog(this, "updateStudent");
	}
	
	public void deleteStudent() {
		Thread t = new Thread() {
			public void run() {
				if (stdList != null) {
					StudentTableModel stm = (StudentTableModel) stdList.getModel();
					if (stdList.getSelectedRow() >= 0) {
						if (JOptionPane.showConfirmDialog(StudentsFrame.this,
								"Do you want to delete student?", "Delete student",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							Student s = stm.getStudent(stdList.getSelectedRow());
							try {
								ms.deleteStudent(s);
								reloadStudents();
							} catch (SQLException e) {
								JOptionPane.showMessageDialog(StudentsFrame.this, e.getMessage());
							}
						}
					} else {
						JOptionPane.showMessageDialog(StudentsFrame.this, "Please select student from the list");
					}
				}
			}
		};
		t.start();
	}
	
	public void showAllStudents() {
		JOptionPane.showMessageDialog(this, "showAllStudents");
	}
	
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentsFrame sf = new StudentsFrame();
					sf.setDefaultCloseOperation(EXIT_ON_CLOSE);
					sf.setVisible(true);
					sf.reloadStudents();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
class GroupPanel extends JPanel {
	
	public Dimension getPreferredSize() {
		return new Dimension(250, 0);
	}
}