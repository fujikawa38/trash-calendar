package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Ward;
import com.example.demo.repository.WardRepository;

@Service
public class WardService {
	private final WardRepository wardRepository;

	public WardService(WardRepository wardRepository) {
		this.wardRepository = wardRepository;
	}

	public List<Ward> getAllWards() {
		return wardRepository.findAll();
	}

}
