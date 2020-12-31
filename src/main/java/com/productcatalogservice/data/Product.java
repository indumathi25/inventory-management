package com.productcatalogservice.data;

import org.springframework.data.annotation.Id;

public class Product {
	@Id	
	private String id; 
	private String name;
	private int quantity;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setId(name);
	}


}
