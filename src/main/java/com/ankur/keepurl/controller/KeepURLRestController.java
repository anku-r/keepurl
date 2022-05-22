package com.ankur.keepurl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ankur.keepurl.app.util.URLUtility;

@RestController
@RequestMapping("api/keepurl")
public class KeepURLRestController {

    @GetMapping(path = "title", params = "url")
    public String getURLTitle(@RequestParam("url") String url) {
	return URLUtility.fetchTitle(url);
    }
}
