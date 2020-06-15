package services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.User;

public class FileReaderService {
	
	public ArrayList<User> getUsersFromFile(String file) {
		ArrayList<User> users = new ArrayList<User>();
		String line;
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(file));
			while (( line = fileReader.readLine()) != null) {
				String[] splitLine = line.split(",");
				User user = new User();
				user.setUsername(splitLine[0]);
				user.setPassword(splitLine[1]);
				user.setName(splitLine[2]);
				
				users.add(user);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

}
