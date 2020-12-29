package com.warehouse.inventorymanagement.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import com.warehouse.inventorymanagement.data.Product;

public interface ProductService {
	
	String fileUpload(MultipartFile file, HttpServletRequest request);

	String saveProduct(Product article);
}
