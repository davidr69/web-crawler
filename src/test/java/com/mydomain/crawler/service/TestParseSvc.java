package com.mydomain.crawler.service;

import com.mydomain.crawler.CrawlerException;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestParseSvc {
	// prefer constructor injection, but unit testing doesn't permit
	@Autowired
	private ParseSvc parseSvc;

	// happy path
	@Test
	public void testClarin() {
		// integration test; if Clarin is down, the test will fail
		try {
			HashSet<String> anchors = parseSvc.parseUriToDocument("https://www.clarin.com");
			Assert.assertTrue(anchors.size() > 0);
		} catch(CrawlerException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	// sad path
	@Test
	public void testGarbled() {
		try {
			parseSvc.parseUriToDocument("https://www.clarin?.com");
			Assert.fail(); // because there's no way this should work
		} catch(CrawlerException e) {
			Assert.assertTrue(true);
		}
	}
}
