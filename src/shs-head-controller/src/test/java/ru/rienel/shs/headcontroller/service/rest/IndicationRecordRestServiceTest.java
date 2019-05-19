package ru.rienel.shs.headcontroller.service.rest;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import org.hamcrest.CoreMatchers;
import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.rienel.shs.headcontroller.domain.ResourceMeter;
import ru.rienel.shs.headcontroller.domain.ResourceType;
import ru.rienel.shs.headcontroller.domain.dto.IndicationRecordDto;
import ru.rienel.shs.headcontroller.repository.ResourceMeterRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class IndicationRecordRestServiceTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ResourceMeterRepository resourceMeterRepository;

	private ResourceMeter testResourceMeter;


	@Before
	public void initializeTestResourceMeter() {
		testResourceMeter = new ResourceMeter();
		testResourceMeter.setAddedTime(ZonedDateTime.now());
		testResourceMeter.setType(ResourceType.ELECTRICITY);
		testResourceMeter.setSerialNumber("abc1234512");
		testResourceMeter = resourceMeterRepository.save(testResourceMeter);
	}

	@After
	public void cleanup() {
		resourceMeterRepository.delete(testResourceMeter);
	}

	/**
	 * Example: request from indication record
	 * {"value":1234567890,"serialNumber":"12345678901234567890123465789012"}
	 */
	@Test
	public void addValidRecordTest() throws JSONException {
		IndicationRecordDto dto = new IndicationRecordDto();
		dto.setSerialNumber("abc1234512");
		dto.setValue(1234567.0);
		ResponseEntity response = this.restTemplate.postForObject("/api/v1/record/add", dto, ResponseEntity.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}