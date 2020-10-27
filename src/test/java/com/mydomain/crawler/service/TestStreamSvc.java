package com.mydomain.crawler.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.InputStream;

@SpringBootTest
public class TestStreamSvc {
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private StreamSvc streamSvc;

	@Test
	public void testClarin() {
		String html = "";
		try(InputStream is = applicationContext.getResource("classpath:clarin.html").getInputStream()) {
			html = streamSvc.getStringFromStream(is);
		} catch(Exception e) {
			Assert.fail();
		}
		Assert.assertTrue(html.startsWith("<!DOCTYPE html>"));
		Assert.assertTrue(html.endsWith("</html>"));
	}

	@Test
	public void testRae() {
		String html = "";
		try(InputStream is = applicationContext.getResource("classpath:rae.html").getInputStream()) {
			html = streamSvc.getStringFromStream(is);
			System.out.println(html.length());
		} catch(Exception e) {
			Assert.fail();
		}
		Assert.assertTrue(html.startsWith("<!DOCTYPE html>"));
		Assert.assertTrue(html.endsWith("</html>\n"));
	}

}
