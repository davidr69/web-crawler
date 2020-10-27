package com.mydomain.crawler.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class HttpSvc {
//	@Bean(name = "MyStream")
	public InputStream getStream(String uri) {
		// would normally use try-with-resources
		try {
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			return conn.getInputStream();
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
