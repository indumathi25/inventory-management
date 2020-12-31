package com.warehouse.inventorymanagement.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import com.warehouse.inventorymanagement.data.Article;

public interface ArticleService {

	boolean loadArticleHandler(MultipartFile file, HttpServletRequest request);

	String saveArticle(Article article);

}