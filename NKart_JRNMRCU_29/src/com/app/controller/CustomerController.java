package com.app.controller;

import java.util.List;
import java.util.Scanner;

import com.app.model.Customer;
import com.app.model.Product;
import com.app.service.CustomerServiceImpl;
import com.app.service.ICustomerService;
import com.app.service.IProductService;
import com.app.service.ProductServiceImpl;
import com.app.util.InputUtil;
import com.app.util.LoginUtil;

public class CustomerController {
	
//	Currently logged in customer object variable
	private static Customer currentCustomer;
//	Logged in flag
	private static boolean loggedIn;
//	Customer service
	private static ICustomerService customerService = new CustomerServiceImpl();
//	Product service
	private static IProductService productService = new ProductServiceImpl();
	
//	Setting the flag to false initially
	public CustomerController() {
		loggedIn = false;
	}
	
//	Customer operations display method
	public void operations() {
		Scanner scanner = InputUtil.getScanner();
		boolean action = true;
		while (action) {
			System.out.println("********Welcome to Customer operations********");
			System.out.println("1. Login\n2. Register\n3. Go back");

			System.out.println("enter your choice:");
			int choice = scanner.nextInt();

			switch (choice) {
//			Login option
			case 1:
				this.login();
				if(loggedIn) {
					this.customerOperations();
				}
				 break;
//				 Register option
			case 2:
				Customer newCustomer = this.readCustomerInfo();
				if(customerService.register(newCustomer)) {
					System.out.println("Customer registered successfully!!");
				} else {
					System.out.println("Error during user registration. Please try again.");
				}
				 break;
			case 3:
				action = false;
				 break;
			default:
				System.out.println("Invalid choice!!");
				 break;
			}
		}
	}
	
//	Customer operations after user is logged in
	private void customerOperations() {
		Scanner scanner = InputUtil.getScanner();
		boolean action = true;
		while (action) {
			System.out.println("********Welcome to Customer operations********");
			System.out.println("1. View products \n2. Add to cart\n3. View cart\n4. Place order\n5. Logout");

			System.out.println("enter your choice:");
			int choice = scanner.nextInt();

			switch (choice) {
//			View all non-expired products
			case 1:
//				Fetch non expired products from product service
				List<Product> products = productService.getNonExpiredProducts();
//				Checking for products in the list
				if(products.size() > 0) {
					for(Product p: products) {
						System.out.println(p.toString());
					}
				} else {
					System.out.println("No product found");
				}
				 break;
//				 Adding product to cart option
			case 2:
//				Taking product id input from user to add a certain product to cart 
				System.out.println("Enter product id: ");
				int productId = InputUtil.getItemId();
//				Checking if product exists, then adding the product to cart
				if(productService.getProduct(productId) != null && customerService.addToCart(currentCustomer.getUsername(), productService.getProduct(productId))) {
					System.out.println("Item added to cart successfully!!");
				} else {
					System.out.println("Error while adding item to cart.");
				}
				 break;
//				 View cart option
			case 3:
				List<Product> cartItems = customerService.viewCart(currentCustomer.getUsername());
				if(cartItems.size() > 0) {
					System.out.println("[");
					for(Product product: cartItems) {
						System.out.println(product.toString());
					}
					System.out.println("]");
				} else {
					System.out.println("Cart is empty!!");
				}
				break;
//				Place order option
			case 4:
				if(customerService.placeOrder(currentCustomer.getUsername())) {
					System.out.println("Order placed successfully!!");
				} else {
					System.out.println("No items in the carts.");
				}
				break;
//				Logout option
			case 5:
				action = false;
				loggedIn = false;
				currentCustomer = null;
				System.out.println("Logged out");
				 break;
			default:
				System.out.println("Invalid choice!!");
				 break;
			}
		}
	}
	
//	Customer Login method
	private void login() {
		String[] loginInfo = LoginUtil.getLoginInfo();
		Customer customer = customerService.login(loginInfo[0], loginInfo[1]);
		if(customer != null) {
			System.out.println("Login successful!!");
			currentCustomer = customer;
			loggedIn = true;
			return;
		}
		System.out.println("Invalid username or password. Please try again.");
		return;
	}
	
//	Customer registration method
	private Customer readCustomerInfo(){
		Scanner scanner = InputUtil.getScanner();
		Customer customer = new Customer();
		System.out.println("Enter username, password, name, email, phone, address:");
		customer.setUsername(scanner.next());
		customer.setPassword(scanner.next());
		customer.setName(scanner.next());
		customer.setEmail(scanner.next());
		customer.setPhone(scanner.next());
		customer.setAddress(scanner.next());

		return customer;
	}
}
