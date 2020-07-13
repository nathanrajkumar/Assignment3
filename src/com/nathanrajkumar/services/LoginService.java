package com.nathanrajkumar.services;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.nathanrajkumar.model.AdminUser;
import com.nathanrajkumar.model.User;

public class LoginService {
	
	static final int MAX_LOGIN_TRIES = 5;
	
	public static void login() throws IOException  {
		List<User> users;
		String usernameInput;
		String passwordInput;

		FileServiceImpl fs = new FileServiceImpl();
		LoginService login = new LoginService();
		MenuService menu = new MenuService();
		users = fs.getUsersFromFile();
		Scanner scanner = new Scanner(System.in);

		int tryCounter = 0;
		boolean isValid = false;
		
		while (tryCounter < MAX_LOGIN_TRIES && isValid == false) {
			tryCounter++;

			System.out.println("Please enter a username:");
			usernameInput = scanner.nextLine();
			System.out.println("Please enter a password:");
			passwordInput = scanner.nextLine();

			User loggedInUser = login.validateUserLogin(users, usernameInput, passwordInput);
			
			if(loggedInUser != null) {
				isValid = true;
				menu.navigate(loggedInUser, scanner, users, fs); 
			} else {
				System.out.println("Invalid login. Please try again");
			}
		}
		if (isValid == false ) {
			System.out.println("Too many failed login attempts, you are now locked out.");
		}
		scanner.close();
	}
	
	public User validateUserLogin(List<User> users, String usernameInput, String passwordInput) {
		for(User user : users) {
			if (user.getUsername().equalsIgnoreCase(usernameInput) && user.getPassword().equals(passwordInput)) {
				return this.checkRole(user);
			} 
		}
		return null;
	}
	
	private User checkRole(User user) {
		if(user.getRole().equals("super_user")) {
			AdminUser admin = new AdminUser(user);
			return admin;
		}
		return user;
	}
	
	public User validateUserLoginAsAdminUser(List<User> users, String usernameInput) {
		for(User user : users) {
			if (user.getUsername().equalsIgnoreCase(usernameInput)) {
				return this.checkRole(user);
			}
		}
		return null;
	}
	
	
}
