package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TrashSchedule;

public interface TrashScheduleRepository extends JpaRepository<TrashSchedule, Long> {
	List<TrashSchedule> findByTownId(Long townId);
}
