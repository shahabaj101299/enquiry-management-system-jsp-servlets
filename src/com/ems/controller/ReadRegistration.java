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

@WebServlet("/readRegistrationAction")
public class ReadRegistration extends HttpServlet {

	public ReadRegistration() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		if (email != null) {
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

			ResultSet allRegistrations = service.getUsersByUserId(userId);

			request.setAttribute("allRegistrations", allRegistrations);

			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/listRegistrations.jsp");
			rd.forward(request, response);

		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
