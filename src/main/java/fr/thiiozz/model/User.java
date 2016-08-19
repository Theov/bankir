package fr.thiiozz.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	private boolean enabled;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy ="user")
    private Set<Authority> roles = new HashSet<Authority>();
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="user")
	private Set<Depense> depenses = new HashSet<Depense>();
	
	@OneToOne
	private User tiers;
	
	public User() {
	}
	
	public User(Long id){
		this.id = id;
	}
	
	public User(String nom, String password) {
		this.username = nom;
		this.password = password;
	}
	
	public User(String nom, String password, boolean enabled) {
		this.username = nom;
		this.password = password;
		this.enabled = enabled;
	}
	
	public User(String nom, String password, boolean enabled, User tiers) {
		this.username = nom;
		this.password = password;
		this.enabled = enabled;
		this.tiers = tiers;
	}
	
	public Long getId(){
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Set<Authority> getRoles() {
		return roles;
	}
	
	public Set<Depense> getDepenses() {
		return depenses;
	}
	
	public boolean getEnabled(){
		return enabled;
	}
	
	public User getTiers() {
		return tiers;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public void setUsername(String nom){
		this.username = nom;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void setRoles(Set<Authority> roles) {
		this.roles = roles;
	}
	
	public void setDepenses(Set<Depense> depenses) {
		this.depenses = depenses;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setTiers(User userTiers) {
		this.tiers = userTiers;
	}

}
