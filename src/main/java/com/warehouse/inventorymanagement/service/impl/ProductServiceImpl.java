package com.warehouse.inventorymanagement.service.impl;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.inventorymanagement.data.Product;
import com.warehouse.inventorymanagement.data.Products;
import com.warehouse.inventorymanagement.model.ProductMessage;
import com.warehouse.inventorymanagement.repositories.ProductRepository;
import com.warehouse.inventorymanagement.service.ProductService;
import com.warehouse.inventorymanagement.utils.Publisher;
//import com.warehouse.inventorymanagement.utils.Producer;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
    private Publisher producer;

	@Override
	public String fileUpload(MultipartFile file, HttpServletRequest request) {
		String message = "";
		try {
			InputStream inputStream = file.getInputStream();
			ObjectMapper mapper = new ObjectMapper();

			List<Products> products = Arrays.asList(mapper.readValue(inputStream, Products.class));

			if (products != null && products.size() > 0) {
				for (Products product : products) {
					if (product.getProduct() != null) {
						for (int i = 0; i < product.getProduct().size(); i++) {
							Product productObject = product.getProduct().get(i);
							Product isExist = productRepository.findByName(productObject.getName());
							if (isExist != null) {
								int existingQuantity = isExist.getQuantity();
								int acummulatedQuantity = existingQuantity + 1;
								productObject.setQuantity(acummulatedQuantity);
								productRepository.save(productObject);
								this.producer.sendMessage(new ProductMessage(productObject.getName(), productObject.getQuantity()));
								continue;
							}
							productObject.setQuantity(1);
							productRepository.save(productObject);
							
							this.producer.sendMessage(new ProductMessage(productObject.getName(), productObject.getQuantity()));
						}
					}
				}
			}
			message = "File Successfully Uploaded to DB";
		} catch (Exception ex) {
			ex.printStackTrace();
			message = "Error in uploding the data";
		}
		return message;
	}

	@Override
	public String saveProduct(Product product) {
		try {
			Product isExist = productRepository.findByName(product.getName());
			if (isExist != null) {
				int existingQuantity = isExist.getQuantity();
				int acummulatedQuantity = existingQuantity + 1;
				product.setQuantity(acummulatedQuantity);
			} else {
				product.setQuantity(1);
			}
			productRepository.save(product);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "Successfully Updated to DB";
	}

}