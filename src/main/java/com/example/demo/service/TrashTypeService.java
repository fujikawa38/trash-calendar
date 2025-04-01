package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TrashType;
import com.example.demo.repository.TrashTypeRepository;

@Service
public class TrashTypeService {
	private final TrashTypeRepository trashTypeRepository;

	public TrashTypeService(TrashTypeRepository trashTypeRepository) {
		this.trashTypeRepository = trashTypeRepository;
	}

	public List<TrashType> getAllTrashTypes() {
		return trashTypeRepository.findAll();
	}

}
