package com.ankur.keepurl.dataaccess.document;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Document(collection = "USER_ACCESS")
@Data
public class UserAccess {
	
	@Id
	private String id;
	
	private String username;
	
	private Boolean isEnabled;
	
	@ToString.Exclude
	private String password;
	
	@DBRef
	private List<UserRole> userRoles;
	
	public void addRole(UserRole role) {
		if (userRoles == null) {
			userRoles = new ArrayList<>();
		}
		userRoles.add(role);
	}
	
}
