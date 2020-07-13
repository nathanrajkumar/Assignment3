package com.nathanrajkumar.services;

import java.io.IOException;
import java.util.List;

import com.nathanrajkumar.model.User;

public interface LoginService {
	
	static final int MAX_LOGIN_TRIES = 5;
	
	public User validateUserLogin(List<User> users, String usernameInput, String passwordInput);
	
	public User validateUserLoginAsAdminUser(List<User> users, String usernameInput);

}
