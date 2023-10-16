package com.davidreisodev.apirestkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ApirestKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestKafkaApplication.class, args);
	}

}
