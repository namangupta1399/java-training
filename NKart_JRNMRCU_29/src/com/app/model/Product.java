package com.app.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
	private int id;
	private String name;
	private double price;
	private int qty;
	private Date manufacturedDate;
	private Date expiryDate;
	
	public Product() {
		
	}
	
	public Product(int id, String name, double price, int qty, Date manufacturedDate, Date expiryDate) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.manufacturedDate= manufacturedDate;
		this.expiryDate = expiryDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Date getManufacturedDate() {
		return manufacturedDate;
	}

	public void setManufacturedDate(Date manufacturedDate) {
		this.manufacturedDate = manufacturedDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");   
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", qty=" + qty + ", manufacturedDate=" + formatter.format(manufacturedDate) + ", expiryDate=" + formatter.format(expiryDate) + " ]";
	}
}
