package com.app.util;

import java.util.Scanner;

public class LoginUtil {
//	Method to take login credentials input and returns the username & password as array with [0] =>  username, [1] => password
	public static String[] getLoginInfo() {
		Scanner scanner = InputUtil.getScanner();
		
		String[] loginInfo = new String[2];
		
		System.out.println("Enter username: ");
		loginInfo[0] = scanner.next();
		System.out.println("Enter password: ");
		loginInfo[1] = scanner.next();
		
		return loginInfo;
	}

}
