package com.ankur.keepurl.dataaccess.document;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "TRASH_LINK")
public class Trash {
	
	@Id
	private String id;
	
	private String title;
	
	private String url;
	
	private LocalDate date;
	
	private String user;
}
