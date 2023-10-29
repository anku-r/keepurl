package com.ankur.keepurl.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class TrashDTO {

    private String id;

    private String title;
    
    private String titleShort;

    private String url;

    private String date;

    @JsonIgnore
    private String user;
}
