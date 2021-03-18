package com.app.service;

public class AdminServiceImpl implements IAdminService {

//	Return boolean value stating if the admin login credentials match or not
	@Override
	public boolean login(String username, String password) {
		return username.equals("admin") && password.equals("password");
	}

}
