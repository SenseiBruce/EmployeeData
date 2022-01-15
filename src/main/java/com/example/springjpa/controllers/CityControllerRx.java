package com.example.springjpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springjpa.entity.City;
import com.example.springjpa.service.CityService;

import net.bytebuddy.utility.RandomString;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/rx")
public class CityControllerRx {
	@Autowired
	private CityService cityService;

	@GetMapping("/one")
	@ResponseBody
	public Mono<City> searchOne() {
		return this.cityService.getCity("Bath", "UK");
	}

	@GetMapping("/")
	@ResponseBody
	public Flux<City> list() {
		return this.cityService.findAll();
	}

	@GetMapping("/add")
	@ResponseBody
	public Mono<Long> add() {
		// To keep the sample simple, we just create a city with a random name.
		String name = RandomString.make(10);
		String country = "USA";
		return this.cityService.add(name, country).map(city -> city.getId());
	}
}
