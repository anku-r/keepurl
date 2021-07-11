package com.ankur.keepurl.logic.manager.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ankur.keepurl.app.util.AppConstants;

import lombok.Data;

@Data
public class UserLinkDto {

	private String id;
	
	@NotNull(message = AppConstants.REQUIRED)
	@Size(min = 1, message = AppConstants.REQUIRED)
	private String title;
	
	@NotEmpty(message = AppConstants.REQUIRED)
	private String url;
}
