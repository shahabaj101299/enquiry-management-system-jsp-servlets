package com.ems.controller;

import java.io.IOException;
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

@WebServlet("/registrationControllerAction")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Registration.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String email = (String) session.getAttribute("email");
		if (email != null) {
			String name = request.getParameter("name");
			String emailId = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			String course = request.getParameter("course");

			DbServiceImpl service = new DbServiceImpl();
			service.connectDb();
			ResultSet result = service.getUserByEmail(email);

			int userId = 0;
			try {
				if (result.next()) {
					userId = result.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			service.createRegistration(name, course, emailId, mobile, userId);

			request.setAttribute("msg", "record added successfully...");

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Registration.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}

	}

}
