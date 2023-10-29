package com.ankur.keepurl.dto;

import javax.validation.constraints.NotEmpty;

import com.ankur.keepurl.utility.AppConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserLinkDTO {

    private String id;

    private String title;
    
    private String titleShort;

    @NotEmpty(message = AppConstants.REQUIRED)
    private String url;

    @JsonIgnore
    private String user;
}
