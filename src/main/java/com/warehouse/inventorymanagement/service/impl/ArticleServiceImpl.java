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
import com.warehouse.inventorymanagement.data.Articles;
import com.warehouse.inventorymanagement.repositories.ArticleRepository;
import com.warehouse.inventorymanagement.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	private final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

	@Override
	public boolean loadArticleHandler(MultipartFile file, HttpServletRequest request) {
		logger.debug("loadArticleHandler service started");
		boolean message = false;
		try {
			InputStream inputStream = file.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			List<Articles> articles = Arrays.asList(mapper.readValue(inputStream, Articles.class));
			if (articles != null && articles.size() > 0) {
				for (Articles article : articles) {
					for (int i = 0; i < article.getArticle().size(); i++) {
						Article articleObject = article.getArticle().get(i);
						Optional<Article> existingArticle = articleRepository.findById(articleObject.get_id());
						if (!existingArticle.isEmpty()) {
							int existingStock = existingArticle.get().getStock();
							int accumulatedstock = existingStock + articleObject.getStock();
							articleObject.setStock(accumulatedstock);
							articleRepository.save(articleObject);
							continue;
						}
						articleRepository.save(article.getArticle().get(i));
						logger.debug("loadArticleHandler service --> Articles saved in DB");
					}
				}
			}
			message = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		logger.debug("loadArticleHandler service completed");
		return message;
	}

}