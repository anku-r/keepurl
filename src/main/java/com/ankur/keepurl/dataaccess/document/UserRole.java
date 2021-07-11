package com.ankur.keepurl.dataaccess.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "USER_ROLE")
public class UserRole {

	@Id
	private String id;
	
	private String role;
	
	public UserRole(String role) {
		this.role = role;
	}
}
