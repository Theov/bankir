package fr.thiiozz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Depense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	@Size(min=2, max=30)
	private String label;
	
	@NotNull
	@Size(min=2, max=30)
	private String proprietaire;
	
	@NotNull
	@Min(1)
	@Max(1000)
	private float montant;
	
	public Depense(){}
	
	public Depense(long id){
		this.id = id;
	}
	
	public Depense(String label, String proprietaire, float montant) {
		this.label = label;
		this.proprietaire = proprietaire;
		this.montant = montant;
	}

	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public float getMontant(){
		return montant;
	}
	
	public void setMontant(float montant){
		this.montant = montant;
	}
	
	public String getLabel(){
		return label;
	}
	
	public void setLabel(String label){
		this.label = label;
	}
	
	public String getProprietaire(){
		return proprietaire;
	}
	
	public void setProprietaire(String proprietaire){
		this.proprietaire = proprietaire;
	}
	
	@Override
    public String toString() {
        return String.format(
                "[id=%d, label='%s', propriétaire='%s', montant='%s']",
                id, label, proprietaire, montant);
    }
}
