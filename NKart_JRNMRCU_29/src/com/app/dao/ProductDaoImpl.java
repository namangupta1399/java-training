package com.app.dao;

import java.util.*;

import com.app.model.Product;

public class ProductDaoImpl implements IProductDao {
	
//	Map to store info on all the products
	private static Map<Integer, Product> products;
//	Variable to be used as product id
	private static int productIdCounter = 1200;

//	Initializing empty map for products 
	static {
		products = new HashMap<Integer, Product>();	
	}

//	Add product DAO method
	@Override
	public Product addProduct(Product product) {
		int initialProductsLength = products.size();
//		Setting product ID
		product.setId(productIdCounter++);
		
		products.put(product.getId(), product);
		
		return products.size() > initialProductsLength ? product : null;
	}

//	Returns product based on product ID
	@Override
	public Product getProduct(int productId) {
		// TODO Auto-generated method stub
		return products.get(productId);
	}

//	Returns the updated product
	@Override
	public Product updateProduct(Product product) {
		Product oldProduct = products.get(product.getId());
		oldProduct.setName(product.getName());
		oldProduct.setPrice(product.getPrice());
		oldProduct.setQty(product.getQty());
		oldProduct.setManufacturedDate(product.getManufacturedDate());
		oldProduct.setExpiryDate(product.getExpiryDate());
		return oldProduct;
	}

//	Returns the removed product
	@Override
	public Product removeProduct(int productId) {
		return products.remove(productId);
	}

//	Returns list of all the products
	@Override
	public List<Product> getAllProducts() {
		List<Product> list = new ArrayList<Product>(products.values());
		return list;
	}
	
}
