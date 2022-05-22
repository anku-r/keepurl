package com.ankur.keepurl.manager.model;

import javax.validation.constraints.NotEmpty;

import com.ankur.keepurl.app.util.AppConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserLinkDTO {

    private String id;

    private String title;

    @NotEmpty(message = AppConstants.REQUIRED)
    private String url;

    @JsonIgnore
    private String user;
}
