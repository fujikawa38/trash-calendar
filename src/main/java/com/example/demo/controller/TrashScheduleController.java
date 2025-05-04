package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.example.demo.entity.TrashSchedule;
import com.example.demo.model.TrashSchedule;
import com.example.demo.service.TrashScheduleService;

@RestController
@RequestMapping("/api/trash-schedules")
public class TrashScheduleController {
	private final TrashScheduleService trashScheduleService;

	public TrashScheduleController(TrashScheduleService trashScheduleService) {
		this.trashScheduleService = trashScheduleService;
	}

	//	@GetMapping("/by-town")
	//	public List<TrashSchedule> getSchedulesByTown(@RequestParam Long townId) {
	//		return trashScheduleService.getScheduleByTown(townId);
	//	}

	@GetMapping("/by-town")
	public List<TrashSchedule> getSchedulesByTown(@RequestParam String townId) {
		return trashScheduleService.getScheduleByTown(townId);
	}
}
