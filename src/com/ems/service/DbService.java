package com.ems.service;

import java.sql.ResultSet;

public interface DbService {

	public void connectDb();

	public boolean verifyLogin(String email, String password);

	public ResultSet getUserByEmail(String email);

	public ResultSet getUsersByUserId(int userId);

	public void deleteRecord(String email);

	public ResultSet getRegistrationsById(int id);

	public void updateRegistrations(int id, String name, String course, String email, String mobile);

}
