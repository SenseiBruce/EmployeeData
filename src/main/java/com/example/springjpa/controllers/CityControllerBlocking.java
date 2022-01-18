package com.example.springjpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springjpa.entity.City;
import com.example.springjpa.repository.CityReactiveRepository;

import net.bytebuddy.utility.RandomString;

@Controller
public class CityControllerBlocking {
	@Autowired
	private CityReactiveRepository cityRepository;

	@GetMapping("/one")
	@ResponseBody
	public City searchOne() {
		return cityRepository.findByNameAndCountryAllIgnoringCase("Bath", "UK");
	}

	@GetMapping("/")
	@ResponseBody
	public Iterable<City> list() {
		return cityRepository.findAll();
	}

	@GetMapping("/add")
	@ResponseBody
	@Transactional
	public Long add() {
		// To keep the sample simple, we just create a city with a random name.
		String name = RandomString.make(10);
		String country = "USA";

		City city = new City(name, country);
		City savedCity = cityRepository.save(city);

		return savedCity.getId();
	}
}
