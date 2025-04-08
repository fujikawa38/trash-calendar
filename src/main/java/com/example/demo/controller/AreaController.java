package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.TownService;
import com.example.demo.service.WardService;

@Controller
public class AreaController {
	private final WardService wardService;
	private final TownService townService;

	@Autowired
	public AreaController(WardService wardService, TownService townService) {
		this.wardService = wardService;
		this.townService = townService;
	}

	@GetMapping("/select-area")
	public String selectArea(Model model) {
		model.addAttribute("wards", wardService.getAllWards());
		model.addAttribute("towns", townService.getAllTowns());
		return "select-area";
	}

}
