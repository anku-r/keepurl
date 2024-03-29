package com.ankur.keepurl.service;

import java.util.List;

import com.ankur.keepurl.dto.UserLinkDTO;

public interface UserLinkService {

    public List<UserLinkDTO> getAllURLs(String user);

    public UserLinkDTO getURLById(String id, String user);

    public UserLinkDTO createUrl(UserLinkDTO userLinkDto, String user);

    public UserLinkDTO updateUrl(UserLinkDTO userLinkDto, String user);

    public void deleteUrl(String id, String user);
}
