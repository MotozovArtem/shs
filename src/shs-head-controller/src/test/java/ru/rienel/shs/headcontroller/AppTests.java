package ru.rienel.shs.headcontroller;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import ru.rienel.shs.headcontroller.config.DatabaseProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties(DatabaseProperties.class)
public class AppTests {

	@Test
	public void contextLoads() {
	}

}
