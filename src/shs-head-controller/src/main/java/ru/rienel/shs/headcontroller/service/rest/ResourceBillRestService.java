package ru.rienel.shs.headcontroller.service.rest;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.rienel.shs.headcontroller.domain.ResourceBill;
import ru.rienel.shs.headcontroller.domain.dto.ResourceBillDto;
import ru.rienel.shs.headcontroller.domain.dto.converters.Converter;
import ru.rienel.shs.headcontroller.repository.ResourceBillRepository;

@RestController
@RequestMapping("api/v1/bill")
public class ResourceBillRestService {
	@Autowired
	private ResourceBillRepository resourceBillRepository;

	@Autowired
	private Converter<ResourceBill, ResourceBillDto> converter;

	@GetMapping("/")
	public List<ResourceBillDto> getAllResourceBills() {
		List<ResourceBillDto> dtoList = new LinkedList<>();
		Iterable<ResourceBill> queryResult = resourceBillRepository.findAll();
		for (ResourceBill resourceBill : queryResult) {
			dtoList.add(converter.fromDomain(resourceBill));
		}
		return dtoList;
	}

	@GetMapping("/{serialNumber}")
	public List<ResourceBillDto> getAllResourceBillsBySerialNumber(@PathVariable("serialNumber") String serialNumber) {
		List<ResourceBillDto> dtoList = new LinkedList<>();
		Iterable<ResourceBill> queryResult = resourceBillRepository.findBySerialNumber(serialNumber);
		for (ResourceBill resourceBill : queryResult) {
			dtoList.add(converter.fromDomain(resourceBill));
		}
		return dtoList;
	}
}
