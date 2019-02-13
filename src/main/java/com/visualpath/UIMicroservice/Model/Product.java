package com.visualpath.UIMicroservice.Model;

public class Product {
	
	public Product(long id, String name, long price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	long id;
	String name;
	long price;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}

}
