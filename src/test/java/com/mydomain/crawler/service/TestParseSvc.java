package com.mydomain.crawler.service;

import com.mydomain.crawler.CrawlerException;

import org.jsoup.select.Elements;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class TestParseSvc {
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ParseSvc parseSvc;

	@Test
	public void testClarin() {
		try {
			Elements elements = parseSvc.parseUriToDocument("https://www.clarin.com");
			elements.stream().forEach( el -> System.out.println(el.absUrl("href")) );
		} catch(CrawlerException e) {
			e.printStackTrace();
		}
	}
}
