package com.warehouse.inventorymanagement.data;

import java.util.List;
import org.springframework.data.annotation.Id;

public class Product {
	@Id	
	private String id; 
	private String name;
	private int quantity;
	private List<Contain_articles> contain_articles;
	
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

	public void setContain_articles(List<Contain_articles> contain_articles) {
		this.contain_articles = contain_articles;
	}

	public List<Contain_articles> getContain_articles() {
		return this.contain_articles;
	}

}
