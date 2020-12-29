package com.warehouse.inventorymanagement.model;

public class ProductMessage {
	public ProductMessage() {

	}

	public ProductMessage(String name, int quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}

	private String name;
	private int quantity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}