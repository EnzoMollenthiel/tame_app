package com.tame.tameApi;

import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import resources.H2TestProfileJPAConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		Application.class,
		H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
class TameApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
