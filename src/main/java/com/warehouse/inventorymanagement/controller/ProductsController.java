package com.warehouse.inventorymanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.warehouse.inventorymanagement.data.Product;
import com.warehouse.inventorymanagement.model.ProductRequest;
import com.warehouse.inventorymanagement.service.ProductService;

@RestController
@RequestMapping("api/v1")
public class ProductsController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/products")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<String> addProductsFromFile(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		if (file == null) {
			return new ResponseEntity<String>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		String message = productService.fileUpload(file, request);

		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@PostMapping(value = "/product", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> addProduct(@Valid @RequestBody ProductRequest productRequest,
			HttpServletRequest request) {
		if (productRequest == null) {
			return new ResponseEntity<String>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		Product product = modelMapper.map(productRequest, Product.class);
		
		String message = productService.saveProduct(product);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}