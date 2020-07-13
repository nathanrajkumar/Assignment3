package com.nathanrajkumar.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.nathanrajkumar.model.User;

public interface FileService {
	
	String fileName = "src/com/nathanrajkumar/resources/dataUserRoles.txt";
	String backupFileDirectory = "src/com/nathanrajkumar/resources/backup/";
	String backupFileName = new SimpleDateFormat("'" + backupFileDirectory + "backup_'yyyyMMddHHmm'.txt'").format(new Date());

	List<User> getUsersFromFile();
	
	void saveToFile(List<User> users) throws IOException;
}
