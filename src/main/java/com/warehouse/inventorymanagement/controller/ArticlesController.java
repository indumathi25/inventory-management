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
import com.warehouse.inventorymanagement.data.Article;
import com.warehouse.inventorymanagement.model.ArticleRequest;
import com.warehouse.inventorymanagement.service.ArticleService;

@RestController
@RequestMapping("api/v1")
public class ArticlesController {

	@Autowired
	private ArticleService articleService;
	
	@PostMapping("/articles")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<String> addArticlesFromFile(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		if (file == null) {
			return new ResponseEntity<String>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		String message = articleService.fileUpload(file, request);

		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@PostMapping(value = "/article", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addArticle(@Valid @RequestBody ArticleRequest articleRequest,
			HttpServletRequest request) {
		if (articleRequest == null) {
			return new ResponseEntity<String>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		Article article = modelMapper.map(articleRequest, Article.class);
		
		String message = articleService.saveArticle(article);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}

