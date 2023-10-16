package com.daividreisodev.apirestkafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApirestKafkaApplicationTests {

	@Test
	@ExtendWith(MockitoExtension.class)
	@MockitoSettings(strictness = Strictness.LENIENT)
	void contextLoads() {
	}

}
