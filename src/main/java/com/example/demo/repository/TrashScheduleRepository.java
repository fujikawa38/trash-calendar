package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.TrashSchedule;

public interface TrashScheduleRepository extends MongoRepository<TrashSchedule, String> {
	List<TrashSchedule> findByTownId(String townId);
}