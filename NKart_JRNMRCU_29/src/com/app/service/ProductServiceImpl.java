package com.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.app.dao.*;
import com.app.model.Product;

public class ProductServiceImpl implements IProductService {

//	Product DAO object
	private static IProductDao productDao = new ProductDaoImpl();

//	Add product service - takes in product object and return boolean - returns true if product added otherwise null
	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return productDao.addProduct(product) != null ? product : null;
	}

//	Return a product based on productId
	@Override
	public Product getProduct(int productId) {
		// TODO Auto-generated method stub
		return productDao.getProduct(productId);
	}

//	Updates the product and returns boolean stating if product is updated or not
	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return productDao.updateProduct(product) != null;
	}

//	Remove product service - takes in product id and returns boolean stating if product is removed or not
	@Override
	public boolean removeProduct(int productId) {
		// TODO Auto-generated method stub
		return productDao.removeProduct(productId) != null;
	}

//	Return list of all the products
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productDao.getAllProducts();
	}

//	Returns list of all non expired products
	@Override
	public List<Product> getNonExpiredProducts() {
		List<Product> products = productDao.getAllProducts();
		List<Product> nonExpiredProducts = new ArrayList<Product>();
		
		for(Product product: products) {
//			Check if expiry date if after current date
			if(product.getExpiryDate().after(new Date())) {
				nonExpiredProducts.add(product);
			}
		}
		
		return nonExpiredProducts;
	}

}
