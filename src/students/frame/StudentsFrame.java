package students.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import students.logic.Group;
import students.logic.ManagementSystem;

public class StudentsFrame extends JFrame{
	
	private static final String MOVE_GR = "moveGroup";
	private static final String CLEAR_GR = "clearGroup";
	private static final String INSERT_ST = "insertStudent";
	private static final String UPDATE_ST = "updateStudent";
	private static final String DELETE_ST = "deleteStudent";
	private static final String ALL_STUDENTS = "allStudent";
	
	//creating groupList, studentList and Year
	ManagementSystem ms = null;
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
		left.add(new JScrollPane(grpList), BorderLayout.CENTER);
		
		//Creating Buttons for groups
		JButton btnMvGr = new JButton("Move");
		btnMvGr.setName(MOVE_GR);
		JButton btnClGr = new JButton("Clear");
		btnClGr.setName(CLEAR_GR);
		
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
		stdList = new JTable(1, 4);
		right.add(new JScrollPane(stdList), BorderLayout.CENTER);
		
		//creating Buttons for Students
		JButton btnAddSt = new JButton("Add");
		btnAddSt.setName(INSERT_ST);
		JButton btnUpdSt = new JButton("Update");
		btnAddSt.setName(UPDATE_ST);
		JButton btnDelSt = new JButton("Delete");
		btnAddSt.setName(DELETE_ST);
		
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
		
		//selecting first group by default
		grpList.setSelectedIndex(0);
		
		setBounds(100, 100, 700, 500);
	}
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentsFrame sf = new StudentsFrame();
					sf.setDefaultCloseOperation(EXIT_ON_CLOSE);
					sf.setVisible(true);
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