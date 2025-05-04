package com.example.demo.repository;

import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;
//import com.example.demo.entity.Item;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Item;

//public interface ItemRepository extends JpaRepository<Item, Long> {
//	List<Item> findByNameContaining(String keyword);
//}

public interface ItemRepository extends MongoRepository<Item, String> {
	List<Item> findByNameContaining(String keyword);
}