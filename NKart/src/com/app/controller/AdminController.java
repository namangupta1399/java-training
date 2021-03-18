package com.app.controller;

import java.util.List;
import java.util.Scanner;

import com.app.model.Customer;
import com.app.model.Product;
import com.app.service.AdminServiceImpl;
import com.app.service.CustomerServiceImpl;
import com.app.service.IAdminService;
import com.app.service.ICustomerService;
import com.app.service.IProductService;
import com.app.service.ProductServiceImpl;
import com.app.util.DateUtil;
import com.app.util.InputUtil;
import com.app.util.LoginUtil;

public class AdminController {
//	Logged in flag
	private static boolean loggedIn;
//	Admin service
	private static IAdminService adminService = new AdminServiceImpl();
//	Product service
	private static IProductService productService = new ProductServiceImpl();
//	Customer service
	private static ICustomerService customerService = new CustomerServiceImpl();
	
//	Admin controller contructor
	public AdminController() {
//		Checking if user is logged in, if not, will ask for creds.
		if(!loggedIn) {
			String[] loginInfo = LoginUtil.getLoginInfo();
			if(adminService.login(loginInfo[0], loginInfo[1])) {
				loggedIn = true;
				System.out.println("Login successful!!");
			} else {
				System.out.println("Invalid username or password. Please try again.");
			}
		}
	}
	
//	Admin operations display
	public void operations() {
		if(loggedIn) {
			Scanner scanner = InputUtil.getScanner();
			boolean action = true;
			while (action) {
				System.out.println("********Welcome to Admin operations********");
				System.out.println("1. Manage products\n2. Manage customers\n3. Logout");

				System.out.println("enter your choice:");
				int choice = scanner.nextInt();

				switch (choice) {
//				Open manage products section
				case 1:
					this.manageProducts();
					break;
//					Open manage customers section
				case 2:
					this.manageCustomers();
					break;
//					Logout option
				case 3:
					action = false;
					loggedIn = false;
					System.out.println("Logged out");
					 break;
				default:
					System.out.println("Invalid choice!!");
					 break;
				}
			}
		}
		return;
	}
	
//	Manage products display method
	private void manageProducts() {
		Scanner scanner = InputUtil.getScanner();
		boolean action = true;
		while (action) {
			System.out.println("********Welcome to Admin operations - Manage Products********");
			System.out.println("1. Add product\n2. Remove product\n3. Update product\n4. View product\n5. View all products\n6. Go back");

			System.out.println("Enter your choice:");
			int choice = scanner.nextInt();

			switch (choice) {
//			Add product option
				case 1:
					Product newProduct = productService.addProduct(this.readProductInfo());
					if(newProduct != null) {
						System.out.println("Product added successfully!!");
						System.out.println(newProduct.toString());
					} else {
						System.out.println("Error while adding product. Please try again.");
					}
					 break;
//					 Remove product option
				case 2:
					System.out.println("Enter product id: ");
//					Invoke and check if a product is removed
					if(productService.removeProduct(InputUtil.getItemId())) {
						System.out.println("Product removed successfully!!");
					} else {
						System.out.println("Error while removing product. Please try again.");
					}
					 break;
//					 Update product option
				case 3:
					System.out.println("Enter product id: ");
					Product oldProduct = productService.getProduct(InputUtil.getItemId());
					if(oldProduct != null) {
						Product updatedProduct = this.updateProductInfo(oldProduct.getId());
						if(productService.updateProduct(updatedProduct)) {
							System.out.println("Product updated successfully!!");
						} else {
							System.out.println("Error while updating product. Please try again.");
						}	
					}
					 break;
//					 View a certain product
				case 4:
					System.out.println("Enter product id: ");
					Product product = productService.getProduct(InputUtil.getItemId());
					if(product != null ) {
						System.out.println(product.toString());
					} else {
						System.out.println("Product not found");
					}
					 break;
//					 View all products option
				case 5:
//					Fetching all products using the product service
					List<Product> products = productService.getAllProducts();
//					Check if any products in the list
					if(products.size() == 0) {
						System.out.println("No product found");
					} else {
						for(Product p: products) {
							System.out.println(p.toString());
						}	
					}
				case 6:
					action = false;
					loggedIn = false;
					 break;
				 default:
					 System.out.println("Invalid choice!!");
			}
		}
	}
	
//	Manage customers display method
	private void manageCustomers() {
		Scanner scanner = InputUtil.getScanner();
		boolean action = true;
		while (action) {
			System.out.println("********Welcome to Admin operations - Manage Customers********");
			System.out.println("1. View customer\n2. View all orders\n3. Go back");

			System.out.println("Enter your choice:");
			int choice = scanner.nextInt();

			switch (choice) {
//			View all customers 
				case 1:
//				Fetching all customers
					List<Customer> customerList = customerService.getAllCustomers();
//					Check if any customer exist
					if(customerList.size() > 0) {
						for(Customer customer: customerList) {
							System.out.println(customer.toString());
						}
					} else {
						System.out.println("No customer found");
					}
					 break;
				case 2:
//					Fetching all orders
					List<Long> orderList = customerService.getAllOrders();
//					Check if orders exist
					if(orderList.size() > 0) {
						System.out.print("[");
						for(Long orderId: orderList) {
							System.out.print(orderId + ", ");
						}
						System.out.println("]");
					} else {
						System.out.println("No orders has been placed yet!!");
					}
					 break;
//					 Logout option
				case 3:
					action = false;
					loggedIn = false;
					 break;
				 default:
					 System.out.println("Invalid choice!!");
			}
		}
	}
	
//	Add product method
	private Product readProductInfo() {
		Scanner scanner = InputUtil.getScanner();
		Product product = new Product();
		System.out.println("Enter product name, price, qty, manufactured date (format: dd/mm/yyyy), expiry date (format: dd/mm/yyyy): ");
		product.setName(scanner.next());
		product.setPrice(scanner.nextDouble());
		product.setQty(scanner.nextInt());
		product.setManufacturedDate(DateUtil.stringToDate(scanner.next()));
		product.setExpiryDate(DateUtil.stringToDate(scanner.next()));
		
		return product;
	}
	
//	Update product method
	private Product updateProductInfo(int productId) {
		Scanner scanner = InputUtil.getScanner();
		Product product = new Product();
		product.setId(productId);
		System.out.println("Enter product name, price, qty, manufactured date (format: dd/mm/yyyy), expiry date (format: dd/mm/yyyy): ");
		product.setName(scanner.next());
		product.setPrice(scanner.nextDouble());
		product.setQty(scanner.nextInt());
		product.setManufacturedDate(DateUtil.stringToDate(scanner.next()));
		product.setExpiryDate(DateUtil.stringToDate(scanner.next()));
		
		return product;
	}

}
