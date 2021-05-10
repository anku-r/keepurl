package com.ankur.keepurl.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("${keepurl.endpoint}")
public class KeepURLRestController {

	@GetMapping(path = "title", params = "url")
	public String getURLTitle(@RequestParam("url") String url) {

		HTMLEditorKit htmlKit = new HTMLEditorKit();
	    HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
	    HTMLEditorKit.Parser parser = new ParserDelegator();
	    try {
			parser.parse(new InputStreamReader(new URL(url).openStream()),
			            htmlDoc.getReader(0), true);
			return htmlDoc.getProperty("title").toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "Cannot Fetch URL Title";
		}
	}
}
