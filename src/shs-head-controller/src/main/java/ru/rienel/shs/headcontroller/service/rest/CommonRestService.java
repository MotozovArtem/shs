package ru.rienel.shs.headcontroller.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.rienel.shs.headcontroller.domain.dto.Common;
import ru.rienel.shs.headcontroller.repository.IndicationRecordRepository;
import ru.rienel.shs.headcontroller.repository.PersonRepository;
import ru.rienel.shs.headcontroller.repository.ResourceBillRepository;
import ru.rienel.shs.headcontroller.repository.ResourceMeterRepository;

@RestController
@RequestMapping("api/v1/")
public class CommonRestService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private ResourceBillRepository resourceBillRepository;

	@Autowired
	private IndicationRecordRepository indicationRecordRepository;

	@Autowired
	private ResourceMeterRepository resourceMeterRepository;


	@GetMapping("/")
	public Common getCommon() {
		Common common = new Common();
		common.setPersonCount(personRepository.count());
		common.setBillsCount(resourceBillRepository.count());
		common.setIndicationsCount(indicationRecordRepository.count());
		common.setMetersCount(resourceMeterRepository.count());
		return common;
	}

}
