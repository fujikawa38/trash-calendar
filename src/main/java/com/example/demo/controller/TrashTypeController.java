package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TrashType;
import com.example.demo.service.TrashTypeService;

@RestController
@RequestMapping("/api/trash-types")
public class TrashTypeController {
	private final TrashTypeService trashTypeService;

	public TrashTypeController(TrashTypeService trashTypeService) {
		this.trashTypeService = trashTypeService;
	}

	@GetMapping
	public List<TrashType> getAllTrashTypes() {
		return trashTypeService.getAllTrashTypes();
	}

}
