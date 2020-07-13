package com.nathanrajkumar.model;

import java.util.List;

import com.nathanrajkumar.services.LoginServiceImpl;

public class AdminUser extends User {
	
	
	public AdminUser() {}	
	public AdminUser(User user) {
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setName(user.getName());
		this.setRole(user.getRole());
	}

	public User loginAsAnotherUser(List<User> users, String usernameInput) {
		LoginServiceImpl login = new LoginServiceImpl();
		return login.validateUserLoginAsAdminUser(users, usernameInput);
	}

}
