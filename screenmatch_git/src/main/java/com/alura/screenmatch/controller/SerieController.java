package com.alura.screenmatch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SerieController {
	
	@GetMapping("/series") // End point.
	public String obterSeries() {
		return "reposta da api web rest".toUpperCase();
	}
}
