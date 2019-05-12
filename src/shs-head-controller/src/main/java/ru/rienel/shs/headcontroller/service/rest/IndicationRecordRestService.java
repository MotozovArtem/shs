package ru.rienel.shs.headcontroller.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.rienel.shs.headcontroller.domain.dto.IndicationRecordDto;
import ru.rienel.shs.headcontroller.repository.IndicationRecordRepository;

@RestController
@RequestMapping("api/v1/record")
public class IndicationRecordRestService {
	private static final Logger log = LoggerFactory.getLogger(IndicationRecordRestService.class);

	private IndicationRecordRepository indicationRecordRepository;

	@Autowired
	public IndicationRecordRestService(IndicationRecordRepository indicationRecordRepository) {
		this.indicationRecordRepository = indicationRecordRepository;
	}

	@PostMapping("/add")
	public ResponseEntity receiveRecord(IndicationRecordDto record) {
		log.info("Received record: {}", record);
		return new ResponseEntity(HttpStatus.OK);
	}

}
