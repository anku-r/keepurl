package com.ankur.keepurl.logic.api.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.ankur.keepurl.app.util.AppConstants;

import lombok.Data;

@Data
@XmlRootElement
public class UserLinkDto {

	private Long id;
	
	@NotNull(message = AppConstants.REQUIRED)
	@Size(min = 1, message = AppConstants.REQUIRED)
	private String title;
	
	@NotEmpty(message = AppConstants.REQUIRED)
	private String url;
}
