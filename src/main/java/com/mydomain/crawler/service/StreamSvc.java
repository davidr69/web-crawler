package com.mydomain.crawler.service;

import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class StreamSvc {
	private final static int BUFFSIZE = 4096;

	public String getStringFromStream(InputStream is) {
		StringBuilder sb = new StringBuilder();
		byte[] buffer = new byte[BUFFSIZE];

		try {
			int bytesRead = is.read(buffer, 0, BUFFSIZE);
			while (bytesRead > 0) {
				sb.append(new String(buffer, 0, bytesRead));
				bytesRead = is.read(buffer, 0, BUFFSIZE);
			}
			is.close();
			return sb.toString();
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
