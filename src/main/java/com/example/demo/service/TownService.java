package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Town;
import com.example.demo.repository.TownRepository;

@Service
public class TownService {
	private final TownRepository townRepository;

	public TownService(TownRepository townRepository) {
		this.townRepository = townRepository;
	}

	public List<Town> getTownsByWard(Long wardId) {
		return townRepository.findByWardId(wardId);
	}

	public List<Town> getAllTowns() {
		return townRepository.findAll();
	}

}
