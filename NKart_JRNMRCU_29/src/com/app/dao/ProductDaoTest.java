package com.app.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.app.dao.IProductDao;
import com.app.dao.ProductDaoImpl;
import com.app.model.Product;

public class ProductDaoTest {
		private static IProductDao productDao = new ProductDaoImpl();
		
		@BeforeClass
		public static void setUpBeforeClass() throws Exception {
	        System.out.println("before class");  
		}

		@Before
		public void setUp() throws Exception {
	        System.out.println("before method test");  
		}
		
		
		@Test
		public void testNegativePrice() throws Exception{
			Product product = new Product(1200,"Bread", -30.0, 2, new Date(), new Date());	
			assertEquals(true, productDao.addProduct(product));
			fail("Negative price entered");
		}
			
		@Test
		public void testNoName() throws Exception {
			Product product = new Product(1300,"", 89.9, 2, new Date(), new Date());	
			assertEquals(false, productDao.addProduct(product));
			fail("No Name of Product");
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
