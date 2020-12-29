package com.warehouse.inventorymanagement.data;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "article")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Articles {
	
	
	private List<Article> inventory;
	
    @JsonProperty("inventory")
	public List<Article> getArticle() {
		return inventory;
	}

    @JsonProperty("inventory")
	public void setArticle(List<Article> inventory) {
		this.inventory = inventory;
	}
	
	
}
