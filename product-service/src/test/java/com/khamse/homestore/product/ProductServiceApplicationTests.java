package com.khamse.homestore.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.khamse.homestore.product.config.ProductTestConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootTest
@ActiveProfiles("test")
@Import(ProductTestConfiguration.class)
class ProductServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
