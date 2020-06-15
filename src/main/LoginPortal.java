package main;

import java.util.ArrayList;
import java.util.Scanner;

import model.User;
import services.FileReaderService;
import services.LoginService;

public class LoginPortal {
	
	static final int MAX_LOGIN_TRIES = 5;

	public static void main(String[] args) {
		login();
	}
	
	public static void login() {
		ArrayList<User> users;
		String usernameInput;
		String passwordInput;

		FileReaderService reader = new FileReaderService();
		LoginService login = new LoginService();
		users = reader.getUsersFromFile("src/resources/data.txt");
		Scanner scanner = new Scanner(System.in);
		
		int tryCounter = 0;
		boolean isValid = false;
		
		while (tryCounter < MAX_LOGIN_TRIES && isValid == false) {
			tryCounter++;

			System.out.println("Please enter a username:");
			usernameInput = scanner.nextLine();
			System.out.println("Please enter a password:");
			passwordInput = scanner.nextLine();
			
			if(login.processLogin(users, usernameInput, passwordInput).getName() != null) {
				isValid = true;
			};
		}
		if (isValid == false ) {
			System.out.println("Too many failed login attempts, you are now locked out.");
		}
		scanner.close();
	}
}
