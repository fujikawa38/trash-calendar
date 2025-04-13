package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Town;

public interface TownRepository extends JpaRepository<Town, Long> {
	List<Town> findByWardIdOrderByIdAsc(Long wardId);
}
