package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Town;

public interface TownRepository extends MongoRepository<Town, String> {
	List<Town> findByWardId(String wardId);
}