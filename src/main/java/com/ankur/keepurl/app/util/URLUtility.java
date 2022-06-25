package com.ankur.keepurl.app.util;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class URLUtility {

    public static String fetchTitle(String URL) {
	log.info("Fetching Title for URL: {}", URL);
	HTMLEditorKit htmlKit = new HTMLEditorKit();
	HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
	HTMLEditorKit.Parser parser = new ParserDelegator();
	URL url = null;
	try {
	    url = new URL(URL);
	    parser.parse(new InputStreamReader(url.openStream()), htmlDoc.getReader(0), true);
	    return htmlDoc.getProperty("title").toString();
	} catch (MalformedURLException e) {
	    log.error("Malformed URL. Returning URL itself");
	    return URL;
	} catch (Exception e) {
	    log.error("Error fetching title. Return first path of URL");
	    String path = url.getPath();
	    return path.substring(1, path.indexOf('/', 1));
	}
    }
}
