package com.ems.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ems.service.DbServiceImpl;

@WebServlet("/updateRegistration")
public class UpdateRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateRegistration() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		DbServiceImpl service = new DbServiceImpl();
		service.connectDb();
		ResultSet result = service.getRegistrationsById(id);

		int registrationId = 0;
		String name = null;
		String course = null;
		String email = null;
		String mobile = null;

		try {
			if (result.next()) {
				registrationId = result.getInt(1);
				name = result.getString(2);
				course = result.getString(3);
				email = result.getString(4);
				mobile = result.getString(5);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("registrationId", registrationId);
		request.setAttribute("name", name);
		request.setAttribute("course", course);
		request.setAttribute("email", email);
		request.setAttribute("mobile", mobile);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/updateRegisration.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String sessionEmail = (String) session.getAttribute("email");
		if (sessionEmail != null) {

			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String course = request.getParameter("course");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");

			DbServiceImpl service = new DbServiceImpl();
			service.connectDb();

			service.updateRegistrations(id, name, course, email, mobile);

			ResultSet result = service.getUserByEmail(sessionEmail);
			int userId = 0;
			try {
				if (result.next()) {
					userId = result.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			ResultSet allRegistrations = service.getUsersByUserId(userId);
			request.setAttribute("allRegistrations", allRegistrations);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/listRegistrations.jsp");
			rd.forward(request, response);

		} else {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}

	}

}
