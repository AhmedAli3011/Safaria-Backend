package com.safaria.backend;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@Disabled("Disabled until security config is fixed")
@ContextConfiguration(classes = TestConfig.class)
class BackendApplicationTests {

	@Test
	void contextLoads() {
		
	}

}
