package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.DotenvUtil;

@RestController
public class EnvCheckController {

	@GetMapping("/env-test")
	public String getEnvVar() {
		return DotenvUtil.get("MONGODB_URI");
	}

}
