package com.ftn.isa.projekat.user.userCore.userRole.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.isa.projekat.user.userCore.user.model.User;

import lombok.Data;

@Entity
@Table  (name= "user_role")
@Data
public class UserRole {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	@Column (name= "role", nullable= false)
	private String role;
	
	@JsonIgnore
	@OneToMany (mappedBy="role")
	private List<User> users;
	
}
