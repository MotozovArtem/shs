package ru.rienel.shs.headcontroller.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.rienel.shs.headcontroller.repository.ResourceBillRepository;

@RestController
@RequestMapping("api/v1/bill")
public class ResourceBillRestService {
	private ResourceBillRepository resourceBillRepository;

	@Autowired
	public ResourceBillRestService(ResourceBillRepository resourceBillRepository) {
		this.resourceBillRepository = resourceBillRepository;
	}


}
