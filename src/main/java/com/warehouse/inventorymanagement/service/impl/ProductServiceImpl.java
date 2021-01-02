package com.warehouse.inventorymanagement.service.impl;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.inventorymanagement.data.Article;
import com.warehouse.inventorymanagement.data.Contain_articles;
import com.warehouse.inventorymanagement.data.Product;
import com.warehouse.inventorymanagement.data.Products;
import com.warehouse.inventorymanagement.model.ProductMessage;
import com.warehouse.inventorymanagement.repositories.ArticleRepository;
import com.warehouse.inventorymanagement.repositories.ProductRepository;
import com.warehouse.inventorymanagement.service.ProductService;
import com.warehouse.inventorymanagement.utils.Publisher;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
    private Publisher producer;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public boolean loadProductsHandler(MultipartFile file, HttpServletRequest request) {
		boolean message = false;
		try {
			InputStream inputStream = file.getInputStream();
			ObjectMapper mapper = new ObjectMapper();

			List<Products> products = Arrays.asList(mapper.readValue(inputStream, Products.class));
			
			logger.debug(String.format("loadProductsHandler --> %s", products));
			
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
							
							logger.debug("loadProductsHandler service --> Products saved in DB");
							this.producer.sendMessage(new ProductMessage(productObject.getName(), productObject.getQuantity()));
						}
					}
				}
			}
			message = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return message;
	}

	
	@Override
	public boolean updateProducts(List<com.productcatalogservice.data.Product> messages) {
		try {
			if (messages.size() > 0) {   
				       
				logger.debug(String.format("updateProducts--> Message --> %s", messages));
				
				for (com.productcatalogservice.data.Product message : messages) {
					if (message != null && message.getName() != null && message.getQuantity() != 0) {
						com.warehouse.inventorymanagement.data.Product product = productRepository
								.findByName(message.getName());
						product.setName(message.getName());
						product.setQuantity(message.getQuantity());
						productRepository.save(product);
						for (Contain_articles article : product.getContain_articles()) {
							//
							Optional<Article> articleObj = articleRepository
									.findById(Integer.parseInt((article.getArt_id())));
							int presentStockQuantity = articleObj.get().getStock();
							int reducingStockQuantity = presentStockQuantity - 1 * Integer.parseInt(article.getAmount_of());
							articleObj.get().setStock(reducingStockQuantity);
							articleRepository.save(articleObj.get());
						}
					}
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}