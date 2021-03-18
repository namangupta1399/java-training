package com.app.service;

import java.util.List;

import com.app.model.Customer;
import com.app.model.Product;

public interface ICustomerService {

	boolean register(Customer customer);
	
	Customer login(String username, String password);
	
	boolean addToCart(String username, Product product);
	
	List<Product> viewCart(String username);
	
	boolean placeOrder(String username);
	
	List<Customer> getAllCustomers();
	
	List<Long> getAllOrders();
}
