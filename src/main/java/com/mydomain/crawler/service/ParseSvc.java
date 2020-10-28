package com.mydomain.crawler.service;

import com.mydomain.crawler.CrawlerException;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParseSvc {
	private static final Logger log = LoggerFactory.getLogger(ParseSvc.class);

	public List<String> parseUriToDocument(String uri) throws CrawlerException {
		try {
			Document doc = Jsoup.connect(uri).timeout(5000).get();
			Elements elements = doc.select("a");		// like document.body.querySelectorAll('a')

			List<String> anchors = new ArrayList<>(); // define in here to avoid defining even in sad path
			elements.forEach( el -> {
				String href = el.absUrl("href");
				if(!anchors.contains(href)) {
					anchors.add(href);
				}
			});

			return anchors;
		} catch(Exception e) {
			log.error(e.getMessage());
			throw new CrawlerException("Error parsing uri", e);
		}
	}

}
