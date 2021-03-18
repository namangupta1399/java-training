package com.app.dao;

import java.util.*;
import java.util.Map.Entry;

import com.app.model.Customer;
import com.app.model.Product;

public class CustomerDaoImpl implements ICustomerDao {
	
//	Map storing all the customers
	private static Map<String, Customer> customers;
//	Map storing cart info of all the users
	private static Map<String, List<Product>> cart;
//	Map storing all the order info
	private static Map<String, Map<Long, List<Product>>> orders;
//	variable to be used as customer id
	private static int customerIdCounter = 1500;

//	Initializing the the empty customers, cart and orders map
	static {
		customers = new HashMap<String, Customer>();
		cart = new HashMap<String, List<Product>>();
		orders = new HashMap<String, Map<Long, List<Product>>>();
	}

//	Add customer DAO method
	@Override
	public Customer createUser(Customer customer) {
//		Check if customer already exists
		if(!customers.containsKey(customer.getUsername())) {
			int initialCustomersLength = customers.size();
			
//			Setting empty cart
			List<Product> cartItems = new ArrayList<Product>();
			cart.put(customer.getUsername(), cartItems);
			
//			Setting orders
			Map<Long, List<Product>> orderItems = new HashMap<>(); 
			orders.put(customer.getUsername(), orderItems);
			
//			Setting cusomter id
			customer.setId(customerIdCounter++);
			customers.put(customer.getUsername(), customer);
//			Returning the customer object if the new customer is added
			return customers.size() > initialCustomersLength ? customer : null;
		}
		return null;
	}

//	Returns a customer based on username
	@Override
	public Customer getUser(String username) {
		return customers.get(username);
	}

//	Add item to the cart of a certain user
	@Override
	public boolean addToCart(String username, Product product) {
		List<Product> products = cart.get(username);
		return products.add(product);
	}

//	Returns the list of products in the cart of a certain user
	@Override
	public List<Product> viewCart(String username) {
		return cart.get(username);
	}

//	Place order based on username - takes all products from cart -> places the order -> removes the products from the cart
	@Override
	public boolean placeOrder(String username) {
		if(cart.get(username).size() > 0) {
			// Setting Unix time in ms as orderID
			Long orderId = System.currentTimeMillis();
//			Creating order
			orders.get(username).put(orderId, this.viewCart(username));
//			Clearing the cart
			cart.get(username).clear();
			return true;
		}
		return false;
	}
	
//	Returns list of all orders
	@Override
	public List<Long> getAllOrders() {
		List<Long> orderList = new ArrayList<>();
		List<Long> keys;
		for (Entry<String, Map<Long, List<Product>>> entry : orders.entrySet())  {
			keys =  new ArrayList<Long>(entry.getValue().keySet());
			orderList.addAll(keys);
		}
		return orderList;
	}

//	Returns list of all customers
	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		List<Customer> list = new ArrayList<Customer>(customers.values());
		return list;
	}

}
