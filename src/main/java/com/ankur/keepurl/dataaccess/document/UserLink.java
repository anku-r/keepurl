package com.ankur.keepurl.dataaccess.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "USER_LINK")
public class UserLink {

	@Id
	private String id;
	
	private String title;
	
	private String url;
}
