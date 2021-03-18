package com.app.controller;

import java.util.Scanner;

import com.app.util.InputUtil;

public class MainController {

	public static void main(String[] args) {
//		Method to start the application
		runApplication();
	}
	
	// Main display method
	public static void runApplication() {
		Scanner scanner = InputUtil.getScanner();
		boolean action = true;
		
		while(action) {
			System.out.println("********WELCOME TO NKart********");
			System.out.println("1. Admin menu\n2. Customer menu\n3. Exit");
			
			System.out.println("Enter your choice:");
			int choice = scanner.nextInt();

			switch (choice) {
//			Admin menu
				case 1:
					AdminController adminController = new AdminController();
					adminController.operations();
					break;
//					Customer menu
				case 2:
					CustomerController customerController = new CustomerController();
					customerController.operations();
					break;
//					Exit option
				case 3:
					action = false;
					break;
				default:
					System.out.println("Invalid choice!!");
			}
		}
	}
	
}
