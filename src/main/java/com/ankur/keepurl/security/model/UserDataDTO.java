package com.ankur.keepurl.security.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ankur.keepurl.app.util.AppConstants;

import lombok.Data;

@Data
public class UserDataDTO {

    @NotNull
    @NotEmpty(message = AppConstants.EMAIL_NOT_FOUND)
    private String email;

    @NotNull
    @NotEmpty(message = AppConstants.PASS_NOT_FOUND)
    private String password;

    @NotNull
    @NotEmpty(message = AppConstants.PASS_NOT_FOUND)
    private String confirmPassword;
}
