package students.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import students.logic.Group;
import students.logic.ManagementSystem;

public class SimpleList extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.println("<B>Group List</B>");
		pw.println("<table border=1>");
		try {
			List<Group> l = ManagementSystem.getInstance().getGroups();
			for (Group gr : l) {
				pw.println("<tr>");
				pw.println("<td>" + gr.getGroupId() + "</td>");
				pw.println("<td>" + gr.getNameGroup() + "</td>");
				pw.println("<td>" + gr.getCurator() + "</td>");
				pw.println("<td>" + gr.getSpeciality() + "</td>");
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		pw.println("</table>");
	}
}
