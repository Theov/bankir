package fr.thiiozz.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public class DepensePartiel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	
	@NotNull
	@Size(min=2, max=30)
	protected String label;
	
	@NotNull
	@Min(1)
	@Max(1000)
	protected float montant;
	
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
