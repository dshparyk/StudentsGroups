package students.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import students.logic.Group;
import students.logic.ManagementSystem;
import students.logic.Student;
import students.web.forms.MainFrameForm;

public class StudentFrameServlet extends HttpServlet {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sId = req.getParameter("studentId");
		//If OK pressed we are updating data (adding new student)
		if (sId != null && req.getParameter("OK") != null) {
			try {
				//If ID > 0, we updating data, otherwise creating new student
				if (Integer.parseInt(sId) > 0) {
					updateStudent(req);
				} else {
					insertStudent(req);
				}
			} catch (SQLException se) {
				se.printStackTrace();
				throw new IOException(se.getMessage());
			} catch (ParseException pe) {
				throw new IOException(pe.getMessage());
			}
		}
		//Getting data to show on main form
		String gs = req.getParameter("groupId");
		String ys = req.getParameter("educationYear");
		int groupId = -1;
		if (gs != null) {
			groupId = Integer.parseInt(gs);
		}
		int year = Calendar.getInstance().get(Calendar.YEAR);
		if (ys != null) {
			year = Integer.parseInt(ys);
		}
		MainFrameForm form = new MainFrameForm();
		try {
			Collection groups = ManagementSystem.getInstance().getGroups();
			Group g = new Group();
			g.setGroupId(groupId);
			if (groupId == -1) {
				Iterator i = groups.iterator();
				g = (Group) i.next();
			}
			Collection students = ManagementSystem.getInstance().getStudentsFromGroup(g, year);
			form.setGroupId(g.getGroupId());
			form.setYear(year);
			form.setGroups(groups);
			form.setStudents(students);
		} catch (SQLException e) {
			throw new IOException(e.getMessage());
		}
		req.setAttribute("form", form);
		getServletContext().getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
	}
	
	private void insertStudent(HttpServletRequest req) throws SQLException, ParseException {
		Student s = prepareStudent(req);
		ManagementSystem.getInstance().insertStudent(s);
	}

	private void updateStudent(HttpServletRequest req) throws SQLException, ParseException {
		Student s = prepareStudent(req);
		ManagementSystem.getInstance().updateStudent(s);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	private Student prepareStudent(HttpServletRequest req) throws ParseException {
		Student s = new Student();
		s.setStudentId(Integer.parseInt(req.getParameter("studentId")));
		s.setFirstName(req.getParameter("firstName").trim());
		s.setLastName(req.getParameter("lastName").trim());
		s.setDateOfBirth(sdf.parse(req.getParameter("dateOfBirth").trim()));
		if (req.getParameter("sex").equals("0")) {
			s.setSex('M');
		} else {
			s.setSex('F');
		}
		s.setGroupId(Integer.parseInt(req.getParameter("groupId").trim()));
		s.setEducationYear(Integer.parseInt(req.getParameter("educationYear").trim()));
		return s;
	}
}