package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Item;

public interface ItemRepository extends MongoRepository<Item, String> {
	List<Item> findByNameContaining(String keyword);
}