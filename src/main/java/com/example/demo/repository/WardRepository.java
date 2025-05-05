package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Ward;

public interface WardRepository extends MongoRepository<Ward, String> {

}