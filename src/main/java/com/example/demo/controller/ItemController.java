package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;

@RestController
@RequestMapping("/api/items")
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;

	@GetMapping("/search")
	public List<Item> searchItems(@RequestParam String keyword) {
		return itemRepository.findByNameContaining(keyword);
	}

}
