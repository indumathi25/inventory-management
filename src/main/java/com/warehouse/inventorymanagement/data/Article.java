package com.warehouse.inventorymanagement.data;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Article
{
	@Id
	private int id;
	private int art_id;
 	private String name;
	private int stock;

	@JsonProperty("art_id")
	public int get_id() {
		return id;
	}

	@JsonProperty("art_id")
	public void set_id(int id) {
		this.id = id;
		setArt_id(id);
	}
	
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
