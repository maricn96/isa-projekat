package com.ftn.isa.projekat.user.userCore.user.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.isa.projekat.user.userCore.userRole.model.UserRole;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	@Column (name="name",nullable = false)
	private String name;
	
	@Column (name="surname", nullable = false)
	private String surname;
	
	@Column (name="city", nullable = false)
	private String city;
	
	@Column (name = "password" , nullable = false)
	private String password;

	@Column (name = "email", nullable = false)
	private String email;
	
	@Column (name = "telephone_number", nullable = false)
	private String telephoneNumber;
	
	@Column (name = "passport", nullable = false)
	private String passport;
	
	@Column (name = "active", nullable = false)
	private boolean active;
	
	
	@JsonIgnore
	@ManyToOne ()
	@JoinColumn (name="role",nullable = true)
    private UserRole role;
	
}
