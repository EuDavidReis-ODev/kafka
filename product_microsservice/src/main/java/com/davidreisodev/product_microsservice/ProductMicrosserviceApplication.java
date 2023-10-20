package com.davidreisodev.product_microsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ProductMicrosserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMicrosserviceApplication.class, args);
	}

}
