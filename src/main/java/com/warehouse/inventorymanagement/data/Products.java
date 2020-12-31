package com.warehouse.inventorymanagement.data;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "product")
public class Products {

	@JsonProperty("products")
	private List<Product> products;

	@JsonProperty("products")
	public List<Product> getProduct() {
		return products;
	}

	@JsonProperty("products")
	public void setProduct(List<Product> product) {
		this.products = product;
	}

}

 