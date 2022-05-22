package com.ankur.keepurl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TrashController {

    @RequestMapping("/trash")
    public String trashIndex() {
	return "trash.html";
    }
}
