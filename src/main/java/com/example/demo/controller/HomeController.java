package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Town;
import com.example.demo.entity.Ward;
import com.example.demo.form.WardForm;
import com.example.demo.repository.TownRepository;
import com.example.demo.repository.WardRepository;

@Controller
public class HomeController {

	@Autowired
	private WardRepository wardRepository;

	@Autowired
	private TownRepository townRepository;

	@GetMapping("/")
	public String index(Model model) {
		List<Ward> wards = wardRepository.findAll();
		List<Town> towns = townRepository.findAll();

		model.addAttribute("wardForm", new WardForm());
		model.addAttribute("wards", wards);
		model.addAttribute("towns", towns);
		return "index";
	}
}
