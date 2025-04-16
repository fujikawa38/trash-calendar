package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.TrashSchedule;

public interface TrashScheduleService {
	List<TrashSchedule> getScheduleByTown(Long townId);
}
