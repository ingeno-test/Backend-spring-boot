package com.springboot.backend.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.app.exeption.ResourceNotFoundException;
import com.springboot.backend.app.model.Voiture;
import com.springboot.backend.app.repository.VoitureRepository;

@RestController
@RequestMapping("/api")
public class VoitureController {
	
	@Autowired
	private VoitureRepository voitureRepository;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/voitures")
	public List<Voiture> getAllVoitures(){
		return voitureRepository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/voitures")
	public Voiture createVoiture(@RequestBody Voiture voiture){
		return voitureRepository.save(voiture);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/voitures/{id}")
	public ResponseEntity<Voiture> getVoitureById(@PathVariable Long id){
		Voiture voiture = voitureRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Voiture qui a l'id="+id+" n'existe pas"));
		return ResponseEntity.ok(voiture);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/voitures/{id}")
	public ResponseEntity<Voiture> updateVoiture(@PathVariable Long id, @RequestBody Voiture voitureDetails){
		Voiture voiture = voitureRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Voiture qui a l'id="+id+" n'existe pas"));
		
		voiture.setMarque(voitureDetails.getMarque());
		voiture.setCouleur(voitureDetails.getCouleur());
		voiture.setPuissance(voitureDetails.getPuissance());
		voiture.setAnnee(voitureDetails.getAnnee());
		voiture.setKilometrage(voitureDetails.getKilometrage());
		
		Voiture updatedVoiture = voitureRepository.save(voiture);
		
		return ResponseEntity.ok(updatedVoiture);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/voitures/{id}")
	public ResponseEntity<Map<String, Boolean>> delVoitureById(@PathVariable Long id){
		Voiture voiture = voitureRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Voiture qui a l'id="+id+" n'existe pas"));
		voitureRepository.delete(voiture);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
