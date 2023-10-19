package com.davidreisodev.microsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class MicrosserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrosserviceApplication.class, args);
	}

}
