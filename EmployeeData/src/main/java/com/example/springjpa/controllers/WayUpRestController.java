package com.example.springjpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjpa.entity.WayUp;
import com.example.springjpa.service.WayUpService;
//my scary merge confict demo
@RestController
public class WayUpRestController {
	
	@Autowired
	WayUpService wayUpService;
	
	@RequestMapping("/getWayUp/{name}")
	public List<WayUp> getWayUpByName(@PathVariable String name){
		
		return wayUpService.getByName(name);
	}
	
	@RequestMapping("/getWayUpAll")
	public List<WayUp> getWayUpAll(){
		
		return wayUpService.getAll();
	}
}
