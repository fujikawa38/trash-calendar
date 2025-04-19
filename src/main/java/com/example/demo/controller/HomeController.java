package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Town;
import com.example.demo.entity.Ward;
import com.example.demo.repository.TownRepository;
import com.example.demo.repository.WardRepository;

@Controller
public class HomeController {

	@Autowired
	private WardRepository wardRepository;

	@Autowired
	private TownRepository townRepository;

	@GetMapping("/")
	public String index(@RequestParam(value = "ward", required = false) Long wardId,
			@RequestParam(value = "town", required = false) Long townId, Model model) {
		List<Ward> wards = wardRepository.findAll();
		List<Town> towns = townRepository.findAll();
		model.addAttribute("wards", wards);
		model.addAttribute("towns", towns);

		if (wardId != null) {
			Optional<Ward> selectedWard = wardRepository.findById(wardId);
			selectedWard.ifPresent(ward -> model.addAttribute("selectedWard", ward.getName()));
		}

		if (townId != null) {
			Optional<Town> selectedTown = townRepository.findById(townId);
			selectedTown.ifPresent(town -> model.addAttribute("selectedTown", town.getName()));
		}
		return "index";
	}
}
