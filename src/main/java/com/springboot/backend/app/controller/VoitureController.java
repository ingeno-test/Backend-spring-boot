package com.springboot.backend.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.app.model.Voiture;
import com.springboot.backend.app.repository.VoitureRepository;

@RestController
@RequestMapping("/api")
public class VoitureController {
	
	@Autowired
	private VoitureRepository voitureRepository;
	
	@GetMapping("/voitures")
	public List<Voiture> getAllVoitures(){
		return voitureRepository.findAll();
	}
}
