package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.TrashType;

public interface TrashTypeRepository extends MongoRepository<TrashType, String> {

}