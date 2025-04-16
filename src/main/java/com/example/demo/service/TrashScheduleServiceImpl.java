package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TrashSchedule;
import com.example.demo.repository.TrashScheduleRepository;

@Service
public class TrashScheduleServiceImpl implements TrashScheduleService {
	private final TrashScheduleRepository trashScheduleRepository;

	public TrashScheduleServiceImpl(TrashScheduleRepository trashScheduleRepository) {
		this.trashScheduleRepository = trashScheduleRepository;
	}

	@Override
	public List<TrashSchedule> getScheduleByTown(Long townId) {
		return trashScheduleRepository.findByTownId(townId);
	}

}
