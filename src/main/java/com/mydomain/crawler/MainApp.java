package com.mydomain.crawler;

import com.mydomain.crawler.service.HttpSvc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.InputStream;

@ComponentScan
@SpringBootApplication
public class MainApp {
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(MainApp.class, args);

		if(args.length != 1) {
			System.err.println("Must supply exactly 1 argument");
			System.exit(1);
		}
		System.out.println(args[0]);
		HttpSvc svc = ctx.getBean(HttpSvc.class);
		InputStream is = svc.getStream(args[0]);
		System.out.println(is);
		if(is != null) {
			try {
				is.close();
			} catch(Exception e) {
				//
			}
		}
	}
}
