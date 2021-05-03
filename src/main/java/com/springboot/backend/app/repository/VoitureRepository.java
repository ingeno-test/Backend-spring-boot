package com.springboot.backend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.backend.app.model.Voiture;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long>{

}
