package ru.rienel.shs.headcontroller.service.rest;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.rienel.shs.headcontroller.domain.ResourceMeter;
import ru.rienel.shs.headcontroller.domain.dto.ResourceMeterDto;
import ru.rienel.shs.headcontroller.domain.dto.converters.Converter;
import ru.rienel.shs.headcontroller.repository.ResourceMeterRepository;

@RestController
@RequestMapping("api/v1/meter")
public class ResourceMeterRestService {
	@Autowired
	private ResourceMeterRepository resourceMeterRepository;

	@Autowired
	private Converter<ResourceMeter, ResourceMeterDto> converter;

	@GetMapping("/")
	public List<ResourceMeterDto> getAllMeters() {
		List<ResourceMeterDto> dtoList = new LinkedList<>();
		Iterable<ResourceMeter> queryResult = resourceMeterRepository.findAll();
		for (ResourceMeter meter : queryResult) {
			dtoList.add(converter.fromDomain(meter));
		}
		return dtoList;
	}

	@GetMapping("/{serialNumber}")
	public ResourceMeterDto getAllMetersBySerialNumber(@PathVariable("serialNumber") String serialNumber) {
		ResourceMeter queryResult = resourceMeterRepository.findBySerialNumber(serialNumber);
		return converter.fromDomain(queryResult);
	}

	@PutMapping("/add")
	public ResourceMeterDto addMeters(@RequestBody ResourceMeterDto meterDto) {
		ResourceMeter meterForAdd = converter.fromDto(meterDto);
		ResourceMeter result = resourceMeterRepository.save(meterForAdd);
		return converter.fromDomain(result);
	}

	@DeleteMapping("/{serialNumber}")
	public boolean deleteMeter(@PathVariable("serialNumber") String serialNumber) {
		return resourceMeterRepository.deleteBySerialNumber(serialNumber);
	}
}
