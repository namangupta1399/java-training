package com.app.service;

import java.util.List;

import com.app.dao.*;
import com.app.model.Customer;
import com.app.model.Product;

public class CustomerServiceImpl implements ICustomerService {
//	Customer DAO object
	private static ICustomerDao customerDao = new CustomerDaoImpl();

//	Register customer service - returns boolean value stating if user registered or not
	@Override
	public boolean register(Customer customer) {
		return customerDao.createUser(customer) != null;
	}

//	Customer login service - returning the customer object if the username and password match otherwise returns null
	@Override
	public Customer login(String username, String password) {
		Customer customer= customerDao.getUser(username);
		if(customer != null && customer.getPassword().equals(password)) {
			return customer;
		}
		return null;
	}

//	Add to cart service - takes username and product object as arguments and returns boolean value stating if product added or not
	@Override
	public boolean addToCart(String username, Product product) {
		return customerDao.addToCart(username, product);
	}

//	Returns list of all the products in the cart of a particular user - takes in username as argument
	@Override
	public List<Product> viewCart(String username) {
		return customerDao.viewCart(username);
	}

//	Place order service - takes in username and returns boolean if order placed or not
	@Override
	public boolean placeOrder(String username) {
		return customerDao.placeOrder(username);
	}

//	Returns a list of all the customers, if no customers - returns empty list
	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerDao.getAllCustomers();
	}

//	Returns list of all the orders
	@Override
	public List<Long> getAllOrders() {
		// TODO Auto-generated method stub
		return customerDao.getAllOrders();
	}

}
