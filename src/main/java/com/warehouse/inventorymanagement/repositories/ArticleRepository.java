package com.warehouse.inventorymanagement.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.inventorymanagement.data.Article;

@Repository
public interface ArticleRepository extends MongoRepository<Article, Integer> {
	
}