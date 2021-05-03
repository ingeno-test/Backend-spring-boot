package com.springboot.backend.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="voitures")
public class Voiture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String marque;
	
	private String couleur;
	
	private int puissance;
	
	private int annee;
	
	private int kilometrage;
	

	public Voiture(long id, String marque, String couleur, int puissance, int annee, int kilometrage) {
		super();
		this.id = id;
		this.marque = marque;
		this.couleur = couleur;
		this.puissance = puissance;
		this.annee = annee;
		this.kilometrage = kilometrage;
	}
	
	public Voiture() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public int getPuissance() {
		return puissance;
	}

	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(int kilometrage) {
		this.kilometrage = kilometrage;
	}
	
	
}
