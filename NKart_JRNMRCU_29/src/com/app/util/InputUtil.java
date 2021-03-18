package com.app.util;

import java.util.Scanner;

public class InputUtil {

	private static Scanner scanner=null;
//	Returns scanner object
	public static Scanner getScanner() {

		if (null == scanner)
			return new Scanner(System.in);
		else
			return scanner;
	}
	
	public static int getItemId() {
		scanner = getScanner();
		return scanner.nextInt();
	}
}
