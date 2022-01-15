package com.example.springjpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springjpa.entity.City;

@Repository	
public interface CityRepository extends CrudRepository<City, Long> {
	City findByNameAndCountryAllIgnoringCase(String name, String country);
}
