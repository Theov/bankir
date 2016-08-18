package fr.thiiozz.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DepensePartiel {
	@Id
	private long id;
	
	private String label;
	
	private float montant;
	
	public long getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public float getMontant() {
		return montant;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setMontant(float montant) {
		this.montant = montant;
	}
	
	public DepensePartiel(){}
	
	public DepensePartiel(long id){
		this.id = id;
	}
	
	public DepensePartiel(long id, String label, float montant){
		this.id = id;
		this.label = label;
		this.montant = montant;
	}
}
