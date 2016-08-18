package fr.thiiozz.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	@Min(1)
	@Max(1000)
	private float montant;
	
	@ManyToOne(cascade=CascadeType.ALL) 
	@JoinColumn(name="user_id")
	private User user;
	
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	@NotNull
	private boolean rembourser;
	
	@NotNull
	private boolean offert;
	
	public Depense(){}
	
	public Depense(long id){
		this.id = id;
	}
	
	public Depense(long id, String label, float montant){
		this.id = id;
		this.label = label;
		this.montant = montant;
	}
	
	public Depense(String label, float montant, User proprietaire) {
		this.label = label;
		this.montant = montant;
		this.user = proprietaire;
		this.dateCreation = new Date();
		this.offert = false;
		this.rembourser = false;
	}
	
	public Depense(String label, float montant, User proprietaire, boolean offert, boolean rembourser) {
		this.label = label;
		this.montant = montant;
		this.user = proprietaire;
		this.dateCreation = new Date();
		this.offert = offert;
		this.rembourser = rembourser;
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
	
	public Date getDateCreation() {
		return dateCreation;
	}
	
	public String getLabel(){
		return label;
	}
	
	public User getUser() {
		return user;
	}
	
	public boolean getRembourser(){
		return rembourser;
	}
	
	public boolean getOffert(){
		return offert;
	}
	
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	public void setMontant(float montant){
		this.montant = montant;
	}
	
	public void setLabel(String label){
		this.label = label;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setOffert(boolean offert) {
		this.offert = offert;
	}
	
	public void setRembourser(boolean rembourser) {
		this.rembourser = rembourser;
	}
	
	@Override
    public String toString() {
        return String.format(
                "[id=%d, label='%s', propriétaire='%s', montant='%s']",
                id, label, user.getUsername(), montant);
    }
}
