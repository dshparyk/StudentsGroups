package students.frame;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import students.logic.Group;

public class GroupDialog extends JDialog implements ActionListener {

	private static final int D_HEIGHT = 150;
	private static final int D_WIDTH = 200;
	private JSpinner spYear;
	private JComboBox groupList;
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	private boolean result = false;
	
	public GroupDialog(int year, List<Group> groups) {
		
		setTitle("Group moving");
		
		//setting layout
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		
		//adding group select
		JLabel l = new JLabel("New Group:");
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		gbl.setConstraints(l, c);
		getContentPane().add(l);
		
		//adding group list
		groupList = new JComboBox(new Vector(groups));
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(groupList, c);
		getContentPane().add(groupList);
		
		//adding year select
		l = new JLabel("New Year:");
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		gbl.setConstraints(l, c);
		getContentPane().add(l);
		
		//increasing group for 1 year
		spYear = new JSpinner(new SpinnerNumberModel(year + 1, 1980, 2050, 1));
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(spYear, c);
		getContentPane().add(spYear);
		
		//adding OK
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.fill = GridBagConstraints.BOTH;
		btnOk.setName("OK");
		btnOk.addActionListener(this);
		gbl.setConstraints(btnOk, c);
		getContentPane().add(btnOk);
		
		//adding Cancel
		btnCancel.setName("Cancel");
		btnCancel.addActionListener(this);
		gbl.setConstraints(btnCancel, c);
		getContentPane().add(btnCancel);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//setting component on the center of screen
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(((int) d.getWidth() - GroupDialog.D_WIDTH) / 2,
				((int) d.getHeight() - GroupDialog.D_HEIGHT) / 2,
				GroupDialog.D_WIDTH, GroupDialog.D_HEIGHT);
		
	}
	
	//getting year that is selected in form
	public int getYear() {
		return ((SpinnerNumberModel) spYear.getModel()).getNumber().intValue();
	}
	
	//getting group that is selected in form
	public Group getGroup() {
		if (groupList.getModel().getSize() > 0) {
			return (Group) groupList.getSelectedItem();
		}
		return null;
	}
	
	public boolean getResult() {
		return result;
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		if (src.getName().equals("OK")) {
			result = true;
		}
		if (src.getName().equals("Cancel")) {
			result = false;
		}
		setVisible(false);
	}

}