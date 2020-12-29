package com.warehouse.inventorymanagement.model;

import javax.validation.constraints.NotNull;

public class ArticleRequest {
	
	@NotNull(message = "Article Id cannot be empty")
	private int art_id;
	
	@NotNull(message = "Name cannot be empty")
	private String name;
	
	@NotNull(message = "Stock cannot be empty")
	private int stock;


	public int getArt_id() {
		return art_id;
	}

	public void setArt_id(int art_id) {
		this.art_id = art_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
