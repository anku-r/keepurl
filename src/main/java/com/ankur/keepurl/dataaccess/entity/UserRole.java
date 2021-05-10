package com.ankur.keepurl.dataaccess.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "useraccess_id")
	private UserAccess userAccess;
	
	private String role;
}
