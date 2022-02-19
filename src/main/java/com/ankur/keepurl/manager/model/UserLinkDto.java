package com.ankur.keepurl.manager.model;

import javax.validation.constraints.NotEmpty;

import com.ankur.keepurl.app.util.AppConstants;

import lombok.Data;

@Data
public class UserLinkDto {

	private String id;
	
	private String title;
	
	@NotEmpty(message = AppConstants.REQUIRED)
	private String url;
}
