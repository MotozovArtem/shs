package ru.rienel.shs.headcontroller.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.rienel.shs.headcontroller.repository.IndicationRecordRepository;

@RestController
@RequestMapping("api/v1/record")
public class IndicationRecordRestService {
	private IndicationRecordRepository indicationRecordRepository;

	@Autowired
	public IndicationRecordRestService(IndicationRecordRepository indicationRecordRepository) {
		this.indicationRecordRepository = indicationRecordRepository;
	}


}
