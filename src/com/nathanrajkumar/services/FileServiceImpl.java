package com.nathanrajkumar.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nathanrajkumar.model.User;

public class FileServiceImpl implements FileService {
	
	public List<User> getUsersFromFile() {
		
		List<User> users = new ArrayList<>();
		
		BufferedReader fileReader = null;
		String line;
		try {
			fileReader = new BufferedReader(new FileReader(fileName));
			while (( line = fileReader.readLine()) != null) {
				String[] splitLine = line.split(",");
				User user = new User(splitLine[0].trim(), splitLine[1].trim(), splitLine[2].trim(), splitLine[3].trim());
				users.add(user);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found. Please check the file path and try again.");
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("There was an I/O Exception.  Please check and try again.");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("File Reader cannot close.");
				e.printStackTrace();
			}
		}
		
		return users;
	}
	
	
	public void saveToFile(List<User> users) throws IOException  {
		File backupFile = new File(backupFileName);
		File file = new File(fileName);
		this.makeBackupFile(file, backupFile);
		try {
			Files.deleteIfExists(file.toPath());
		} catch (IOException e1) {
			System.out.println("Cannot delete file : " + file.getName());
			e1.printStackTrace();
		}
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(new FileWriter(file));
			for(User user : users) {
				String line = user.getUsername() + ", " + user.getPassword() + ", " + user.getName() + ", " + user.getRole() + "\n";
				writer.write(line);
			}
		} catch (IOException e) {
			System.out.println("Cannot write to file. There was an I/O Exception.  Please check and try again.");
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private void makeBackupFile(File oldFile, File backupFile) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(oldFile);
	        os = new FileOutputStream(backupFile);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
}
