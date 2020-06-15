package services;

import java.util.ArrayList;

import model.User;

public class LoginService {
	
	public static User processLogin(ArrayList<User> users, String usernameInput, String passwordInput) {
		User validUser = new User();
		for(User user : users) {
			if (user.getUsername().equalsIgnoreCase(usernameInput) && user.getPassword().equals(passwordInput)) {
				validUser = user;
			}
		}
		if (validUser.getName() != null) {
			System.out.println("Welcome " + validUser.getName());
		} else {
			System.out.println("Invalid login. Please try again");
		}
		return validUser;
		
	}
}
