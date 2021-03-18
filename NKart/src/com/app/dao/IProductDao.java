package com.app.dao;

import java.util.List;

import com.app.model.Product;

public interface IProductDao {
	
	Product addProduct(Product product);
	
	Product getProduct(int productId);
	
	Product updateProduct(Product product);
	
	Product removeProduct(int productId);
	
	List<Product> getAllProducts();
}
