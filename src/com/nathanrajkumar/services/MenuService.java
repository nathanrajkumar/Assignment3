package com.nathanrajkumar.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.nathanrajkumar.model.User;

public interface MenuService {
	
	static final String LOGIN_AS_ANOTHER_USER = "Login as another User";
	static final String UPDATE_USERNAME = "Update Username";
	static final String UPDATE_PASSWORD = "Update Password";
	static final String UPDATE_NAME = "Update Name";
	static final String EXIT = "Exit";
	static final List<String> BASE_OPTIONS = Collections.unmodifiableList(Arrays.asList(UPDATE_USERNAME, UPDATE_PASSWORD, UPDATE_NAME, EXIT));
	
	public List<String> getOptions(User user);
	
	public void displayMenu(List<String> options);
	
	public void navigate(User user, Scanner scanner, List<User> users, FileServiceImpl fs) throws IOException;

}
