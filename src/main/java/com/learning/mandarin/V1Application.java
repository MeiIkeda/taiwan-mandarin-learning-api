package com.learning.mandarin;

import io.sentry.Sentry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;



@SpringBootApplication
@EntityScan("com.learning.mandarin.domain")
public class V1Application {

	public static void main(String[] args) {

		Sentry.init();
		SpringApplication.run(V1Application.class, args);
	}

}
