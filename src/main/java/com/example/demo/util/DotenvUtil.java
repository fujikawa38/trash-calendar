package com.example.demo.util;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvUtil {

	private static final Dotenv dotenv = Dotenv.configure().directory("./").ignoreIfMalformed().ignoreIfMissing()
			.load();

	public static String get(String key) {
		return dotenv.get(key);
	}

}
