package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TrashType;

public interface TrashTypeRepository extends JpaRepository<TrashType, Long> {

}
