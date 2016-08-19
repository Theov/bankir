package fr.thiiozz.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Depense extends DepensePartiel{
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	@NotNull
	private boolean rembourser;
	
	@NotNull
	private boolean offert;
	
	public Depense(){
		super();
	}
	
	public Depense(long id){
		super();
		this.id = id;
	}
	
	public Depense(long id, String label, float montant){
		super();
		this.id = id;
		this.label = label;
		this.montant = montant;
	}
	
	public Depense(String label, float montant, User proprietaire) {
		super();
		this.label = label;
		this.montant = montant;
		this.user = proprietaire;
		this.dateCreation = new Date();
		this.offert = false;
		this.rembourser = false;
	}
	
	public Depense(String label, float montant, User proprietaire, boolean offert, boolean rembourser) {
		super();
		this.label = label;
		this.montant = montant;
		this.user = proprietaire;
		this.dateCreation = new Date();
		this.offert = offert;
		this.rembourser = rembourser;
	}
	
	
	public Date getDateCreation() {
		return dateCreation;
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
