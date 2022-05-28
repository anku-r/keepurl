package com.ankur.keepurl.dataaccess.document;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "USER_DATA")
@Data
public class UserData {

    @Id
    private String username;

    private Boolean isEnabled;

    @ToString.Exclude
    private String password;

    @Setter(value = AccessLevel.NONE)
    private List<String> roles;

    public void addRole(String role) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(role);
    }
}