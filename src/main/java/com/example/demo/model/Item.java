package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "items")
public class Item {

	@Id
	private String id;

	private String name;

	private String trashTypeId;
	private String trashTypeId2;
}
