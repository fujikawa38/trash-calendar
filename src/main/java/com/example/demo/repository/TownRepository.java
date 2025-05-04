package com.example.demo.repository;

import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;
//import com.example.demo.entity.Town;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Town;

//public interface TownRepository extends JpaRepository<Town, Long> {
//	List<Town> findByWardIdOrderByIdAsc(Long wardId);
//}

public interface TownRepository extends MongoRepository<Town, String> {
	List<Town> findByWardId(String wardId);
}