package com.ems.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ems.service.DbServiceImpl;

@WebServlet("/loginAction")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public loginController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		DbServiceImpl dbservice = new DbServiceImpl();

		dbservice.connectDb();
		Boolean status = dbservice.verifyLogin(email, password);

		if (status) {

			HttpSession session = request.getSession(true);
			session.setAttribute("email", email);
			session.setMaxInactiveInterval(10);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Registration.jsp");
			rd.forward(request, response);

		} else {
			request.setAttribute("status", "invalid login details >>> wrong id/password");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);

		}

	}

}
