package com.warehouse.inventorymanagement.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public interface ArticleService {

	boolean loadArticleHandler(MultipartFile file, HttpServletRequest request);

}
