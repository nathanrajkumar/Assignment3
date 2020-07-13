package com.nathanrajkumar.main;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.nathanrajkumar.model.User;
import com.nathanrajkumar.services.FileServiceImpl;
import com.nathanrajkumar.services.LoginService;
import com.nathanrajkumar.services.MenuService;

public class LoginPortal {
	public static void main(String[] args) throws IOException   {
		LoginService.login();
	}
}
