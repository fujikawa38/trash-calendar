package com.example.demo.model;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

//import com.example.demo.entity.Town;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "wards")
public class Ward {

	@Id
	private String id;

	private String name;

	//	@DBRef
	//	private List<Town> towns;

}
