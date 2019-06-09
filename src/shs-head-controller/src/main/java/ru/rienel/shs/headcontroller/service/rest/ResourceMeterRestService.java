package ru.rienel.shs.headcontroller.service.rest;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.rienel.shs.headcontroller.domain.ResourceMeter;
import ru.rienel.shs.headcontroller.repository.ResourceMeterRepository;

@RestController
@RequestMapping("api/v1/meter")
public class ResourceMeterRestService {
	@Autowired
	private ResourceMeterRepository resourceMeterRepository;

	@GetMapping("/")
	public List<ResourceMeter> getAllMeters() {
		List<ResourceMeter> resultList = new LinkedList<>();
		Iterable<ResourceMeter> queryResult = resourceMeterRepository.findAll();
		for (ResourceMeter meter : queryResult) {
			resultList.add(meter);
		}
		return resultList;
	}

	@GetMapping("/{serialNumber}")
	public ResourceMeter getAllMetersBySerialNumber(@PathVariable("serialNumber") String serialNumber) {
		return resourceMeterRepository.findBySerialNumber(serialNumber);
	}

	@PutMapping("/")
	public ResourceMeter addMeters(@RequestBody ResourceMeter resourceMeter) {
		resourceMeter.setAddedTime(ZonedDateTime.now());
		return resourceMeterRepository.save(resourceMeter);
	}

	@PostMapping("/{serialNumber}")
	public ResourceMeter updateMeter(@PathVariable("serialNumber") String serialNumber, @RequestBody ResourceMeter resourceMeter) {
		ResourceMeter meterForUpdate = resourceMeterRepository.findBySerialNumber(serialNumber);
		meterForUpdate.setType(resourceMeter.getType());
		meterForUpdate.setNeighbors(resourceMeter.getNeighbors());
		meterForUpdate.setAddedTime(resourceMeter.getAddedTime());
		meterForUpdate.setVerification(resourceMeter.getVerification());
		return resourceMeterRepository.save(meterForUpdate);
	}

	@DeleteMapping("/{serialNumber}")
	public Boolean deleteMeter(@PathVariable("serialNumber") String serialNumber) {
		return resourceMeterRepository.deleteBySerialNumber(serialNumber);
	}
}
