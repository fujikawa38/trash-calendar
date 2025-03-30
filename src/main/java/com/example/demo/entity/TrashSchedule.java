package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "trash_schedules")
public class TrashSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "town_id", nullable = false)
	private Town town;

	@ManyToOne
	@JoinColumn(name = "trash_type_id", nullable = false)
	private TrashType trashType;

	@Column(nullable = false)
	private String weekOfMonth;

	@Column(nullable = false)
	private String dayOfWeek;
}
