package com.example.demo.service;

import java.util.List;

//import com.example.demo.entity.TrashSchedule;
import com.example.demo.model.TrashSchedule;

//public interface TrashScheduleService {
//	List<TrashSchedule> getScheduleByTown(Long townId);
//}

public interface TrashScheduleService {
	List<TrashSchedule> getScheduleByTown(String townId);
}