package com.warehouse.inventorymanagement.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.warehouse.inventorymanagement.model.Response;
import com.warehouse.inventorymanagement.service.ProductService;

@RestController
@RequestMapping("/inventory/api/v1")
public class ProductsController {

	@Autowired
	private ProductService productService;
	private final Logger logger = LoggerFactory.getLogger(ProductsController.class);

	@PostMapping("/products")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Response> addProductsFromFile(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		Response response = new Response();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		logger.info("addProductsFromFile request recieved");
		if (file == null) {
			logger.error("file is not present in request");
			map.put("productssAdded", false);
			list.add(map);
			response.setCode(400);
			response.setData(list);
			response.setMessage("File should be attached");
			return ResponseEntity.status(400).body(response);
		}
		
		boolean message = productService.loadProductsHandler(file, request);
		if (!message) {
			logger.error("Error in uploading the products");
			map.put("productssAdded", false);
			list.add(map);
			response.setCode(400);
			response.setData(list);
			response.setMessage("Error in uploading the product");
			return ResponseEntity.status(400).body(response);
		} 
		
		logger.info("File Successfully Uploaded to DB");
		map.put("articlesAdded", true);
		list.add(map);
		response.setCode(200);
		response.setData(list);
		response.setMessage("Products loaded successfully");
		return ResponseEntity.status(200).body(response);

	}

}