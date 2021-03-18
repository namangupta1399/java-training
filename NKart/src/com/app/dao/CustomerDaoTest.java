package com.app.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.app.dao.CustomerDaoImpl;
import com.app.dao.ICustomerDao;
import com.app.model.Customer;

public class CustomerDaoTest {

	private static ICustomerDao customerDao = new CustomerDaoImpl();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
        System.out.println("before class");  
	}


	@Before
	public void setUp() throws Exception {
        System.out.println("before method test");  
	}

	@Test
	public void testCustomerCreationWithBlankUsername() throws Exception{
		System.out.println("Customer Creation With Blank Username test");
		Customer customer = new Customer("", "pass", "Naman Gupta", "naman@gmail.com", "23423423", "delhi");
		assertEquals(customer, customerDao.createUser(customer));
		fail("username cannot be blank");
	}
	@Test
	
	public void testCustomerCreationAlreadyExisting() throws Exception{
		System.out.println("Customer Creation Already Existing test");
		Customer customer = new Customer("naman", "pass", "Naman Gupta", "naman@gmail.com", "342324", "Delhi");
		customerDao.createUser(customer);
		assertEquals(customer, customerDao.createUser(customer));
		fail("already exists");
	}

	@After
	public void tearDown() throws Exception {
        System.out.println("after method test");  
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
        System.out.println("after class");  
	}

}
