package com.nathanrajkumar.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.nathanrajkumar.model.User;

public interface FileService {
	
	String FILENAME = "src/com/nathanrajkumar/resources/dataUserRoles.txt";
	String BACKUP_FILE_DIRECTORY = "src/com/nathanrajkumar/resources/backup/";
	String BACKUP_FILE_NAME = new SimpleDateFormat("'" + BACKUP_FILE_DIRECTORY + "backup_'yyyyMMddHHmm'.txt'").format(new Date());

	List<User> getUsersFromFile();
	
	void saveToFile(List<User> users) throws IOException;
}
