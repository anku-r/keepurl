package com.ankur.keepurl.dataaccess.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class UserAccess {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	@Column(columnDefinition = "boolean default true")
	private Boolean isEnabled;
	
	@ToString.Exclude
	private String password;
	
	@OneToMany(mappedBy = "userAccess", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<UserRole> userRoles;	
}
