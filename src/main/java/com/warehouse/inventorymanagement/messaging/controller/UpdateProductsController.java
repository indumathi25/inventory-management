package com.warehouse.inventorymanagement.messaging.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.productcatalogservice.data.Product;
import com.warehouse.inventorymanagement.service.ProductService;


@Service
public class UpdateProductsController {
	
	@Autowired
	private ProductService productService;

	private final Logger logger = LoggerFactory.getLogger(UpdateProductsController.class);

	@KafkaListener(topics = "updateInventory", groupId = "updateInventory")
	public void consume(List<Product> messages) {
		boolean isUpdated = productService.updateProducts(messages);
		logger.info("Products updated : " + isUpdated);
	}
}
