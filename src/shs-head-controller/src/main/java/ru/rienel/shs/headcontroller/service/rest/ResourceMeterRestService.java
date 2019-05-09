package ru.rienel.shs.headcontroller.service.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.rienel.shs.headcontroller.repository.ResourceMeterRepository;

@RestController
@RequestMapping("api/v1/meter")
public class ResourceMeterRestService {
	private ResourceMeterRepository resourceMeterRepository;

	public ResourceMeterRestService(ResourceMeterRepository resourceMeterRepository) {
		this.resourceMeterRepository = resourceMeterRepository;
	}
}
