package com.warehouse.inventorymanagement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import com.productcatalogservice.data.Product;

public interface ProductService {
	
	boolean loadProductsHandler(MultipartFile file, HttpServletRequest request);
	
	boolean updateProducts(List<Product> messages);
}
