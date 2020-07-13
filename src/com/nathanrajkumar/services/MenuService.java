package com.nathanrajkumar.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.nathanrajkumar.model.AdminUser;
import com.nathanrajkumar.model.User;

public class MenuService {
	
	static final String LOGIN_AS_ANOTHER_USER = "Login as another User";
	static final String UPDATE_USERNAME = "Update Username";
	static final String UPDATE_PASSWORD = "Update Password";
	static final String UPDATE_NAME = "Update Name";
	static final String EXIT = "Exit";
	static final List<String> BASE_OPTIONS = Collections.unmodifiableList(Arrays.asList(UPDATE_USERNAME, UPDATE_PASSWORD, UPDATE_NAME, EXIT));
	
	public List<String> getOptions(User user) {
		List<String> options = new ArrayList<>();
		System.out.println("Welcome: " + user.getName());
		System.out.println("-----");
		System.out.println("Please choose from the following options: ");
		if (user instanceof AdminUser) {
			options.add(LOGIN_AS_ANOTHER_USER);
		}
		for (String option : BASE_OPTIONS) {
			options.add(option);
		}
		return options;
	}
	
	public void displayMenu(List<String> options) {
		int menuCounter;
		if (options.size() > BASE_OPTIONS.size()) { 
			menuCounter = 0; 
		} else { 
			menuCounter = 1;
		};
		for (String option : options) {
			System.out.println("(" + menuCounter + ") " + option);
			menuCounter++;
		}
	}
	
	public void navigate(User user, Scanner scanner, List<User> users, FileServiceImpl fs) throws IOException {
		validateChoice(scanner, user, users, fs);
	}
	
	private void navigateToOption(int menuChoice, User loggedInUser, Scanner scanner, List<User> users, FileServiceImpl fs) throws IOException {
			switch(menuChoice) {
				case 0: 
					System.out.println("Navigating to Option 0: " + LOGIN_AS_ANOTHER_USER);
					System.out.println("Which user would you like to login as? (Type in a valid username)");
					String usernameInput = scanner.nextLine();
					AdminUser admin = new AdminUser(loggedInUser);
					User newLoginUser = new User();
					newLoginUser = admin.loginAsAnotherUser(users, usernameInput);
					if (newLoginUser != null) {
						validateChoice(scanner, newLoginUser, users, fs);
					} else {
						throw new NullPointerException();
					}
					break;
				case 1: 
					loggedInUser = updateUser(menuChoice, UPDATE_USERNAME, "username", loggedInUser, scanner, users, fs);
					validateChoice(scanner, loggedInUser, users, fs);
					break;
				case 2: 
					loggedInUser = updateUser(menuChoice, UPDATE_PASSWORD, "password", loggedInUser, scanner, users, fs);
					validateChoice(scanner, loggedInUser, users, fs);
					break;
				case 3: 
					loggedInUser = updateUser(menuChoice, UPDATE_NAME, "name", loggedInUser, scanner, users, fs);
					validateChoice(scanner, loggedInUser, users, fs);
					break;
				case 4:
					System.out.println("Terminating Application");
					scanner.close();
					break;
				default: 
					System.out.println(menuChoice + " is an invalid choice. Please choose a valid number");
					validateChoice(scanner, loggedInUser, users, fs);
					break;
			}
	}
	
	private void validateChoice(Scanner scanner, User loggedUser, List<User> users, FileServiceImpl fs) throws IOException {
		int menuChoice;
		boolean choiceValid = false;
		while (choiceValid == false) {
			try {
				this.displayMenu(this.getOptions(loggedUser));
				menuChoice = Integer.parseInt(scanner.nextLine());
				this.navigateToOption(menuChoice, loggedUser, scanner, users, fs);
				choiceValid = true;
			} catch (NumberFormatException e) {
				System.out.println("Not a valid integer.  Please make sure choice is numeric");
			} catch (NullPointerException e) {
				System.out.println("User does not exist in DB. Please try again");
			}
		}
	}

	private User updateUser(int menuChoice, String action, String valueToBeChanged, User loggedUser, Scanner scanner, List<User> users, FileServiceImpl fs) throws IOException {
		System.out.println("Navigating to Option " + menuChoice + " : " + action);
		System.out.println("Please type in your new " + valueToBeChanged + ": ");
		String input = scanner.nextLine();
		for (User user : users ) {
			if(action.equals(UPDATE_USERNAME) || action.equals(UPDATE_PASSWORD)) {
				if (loggedUser.getName().equals(user.getName())) {
					if(action.equals(UPDATE_USERNAME)) {
						user.setUsername(input);
						loggedUser.setUsername(input);
					} else { 
						user.setPassword(input);
						loggedUser.setPassword(input);
					}
				} 
			} else if (action.equals(UPDATE_NAME)) {
				if (loggedUser.getUsername().equals(user.getUsername())) {
					user.setName(input);
					loggedUser.setName(input);
				}
			}
			
		}
		Collections.sort(users);
		fs.saveToFile(users);
		return loggedUser;
	}
}
