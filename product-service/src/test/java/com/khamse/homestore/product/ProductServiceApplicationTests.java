package com.khamse.homestore.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
    "firebase.enabled=false",
    "spring.cloud.gcp.storage.enabled=false"
})
class ProductServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
