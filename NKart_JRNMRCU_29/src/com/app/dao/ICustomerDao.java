package com.app.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.app.model.Customer;
import com.app.model.Product;

public interface ICustomerDao {
	Customer createUser(Customer customer);
	
	Customer getUser(String username);
	
	boolean addToCart(String username, Product product);

	List<Product> viewCart(String username);
	
	boolean placeOrder(String username);
	
	List<Long> getAllOrders();
	
	List<Customer> getAllCustomers();
}
