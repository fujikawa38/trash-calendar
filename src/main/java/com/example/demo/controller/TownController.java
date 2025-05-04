package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.example.demo.entity.Town;
import com.example.demo.model.Town;
import com.example.demo.service.TownService;

@RestController
@RequestMapping("/api/towns")
public class TownController {
	private final TownService townService;

	public TownController(TownService townService) {
		this.townService = townService;
	}

	//	@GetMapping("/by-ward")
	//	public List<Town> getTownByWard(@RequestParam Long wardId) {
	//		return townService.getTownsByWard(wardId);
	//	}

	@GetMapping("/by-ward")
	public List<Town> getTownByWard(@RequestParam String wardId) {
		return townService.getTownsByWard(wardId);
	}

}
