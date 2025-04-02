package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Ward;
import com.example.demo.service.WardService;

@RestController
@RequestMapping("/api/wards")
public class WardController {
	private final WardService wardService;

	public WardController(WardService wardService) {
		this.wardService = wardService;
	}

	@GetMapping
	public List<Ward> getAllWards() {
		return wardService.getAllWards();
	}

}
