package com.ets.gti525.domain.request;

public class CreateUserRequest {

	private String prenom;
	private String nom;
	private String courriel;
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getCourriel() {
		return courriel;
	}
	
	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}
	
}
