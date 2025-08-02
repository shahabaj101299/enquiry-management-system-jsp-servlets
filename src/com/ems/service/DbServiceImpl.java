package com.ems.service;

import java.sql.*;

public class DbServiceImpl implements DbService {

	private Connection con;
	private Statement stmt;

	@Override
	public void connectDb() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emsdb", "root", "root");
			stmt = con.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean verifyLogin(String email, String password) {
		try {
			ResultSet result = stmt
					.executeQuery("select * from users where email='" + email + "' and password='" + password + "'");
			return result.next();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ResultSet getUserByEmail(String email) {
		try {
			ResultSet result = stmt.executeQuery("select * from users where email='" + email + "'");
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void createRegistration(String name, String course, String email, String mobile, int userId) {
		try {
			stmt.executeUpdate("insert into registration (name, course, email, mobile, user_id) values ('" + name
					+ "','" + course + "','" + email + "','" + mobile + "','" + userId + "') ");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet getUsersByUserId(int userId) {
		try {
			ResultSet result = stmt.executeQuery("select * from registration where user_id='" + userId + "'  ");
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteRecord(String email) {

		try {
			stmt.executeUpdate("delete from registration where email = '" + email + "'");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ResultSet getRegistrationsById(int id) {
		try {
			ResultSet result = stmt.executeQuery("select * from registration where id = '" + id + "'");
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateRegistrations(int id, String name, String course, String email, String mobile) {
		try {
			int rows = stmt.executeUpdate("update registration set name='" + name + "', course='" + course
					+ "', email='" + email + "', mobile='" + mobile + "' where id='" + id + "'");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
