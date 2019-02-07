package com.ftn.isa.projekat.purchases.purchasesCore.adminLink.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.Data;

@Entity
@Table(name="admin_link")
@Data
public class AdminLink {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	@Column (name = "user_id",nullable = false)
	private Long userId;
	
	@Column (name = "user_role", nullable = false)
	private String role;
	
	@Column (name = "service_id", nullable=false)
	private Long serviceId;

}
