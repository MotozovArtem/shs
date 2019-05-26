package ru.rienel.shs.headcontroller.service.rest;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.rienel.shs.headcontroller.domain.ResourceBill;
import ru.rienel.shs.headcontroller.repository.ResourceBillRepository;

@RestController
@RequestMapping("api/v1/bill")
public class ResourceBillRestService {
	@Autowired
	private ResourceBillRepository resourceBillRepository;

	@GetMapping("/")
	public List<ResourceBill> getAllResourceBills() {
		List<ResourceBill> resultList = new LinkedList<>();
		Iterable<ResourceBill> queryResult = resourceBillRepository.findAll();
		for (ResourceBill resourceBill : queryResult) {
			resultList.add(resourceBill);
		}
		return resultList;
	}

	@GetMapping("/{serialNumber}")
	public ResourceBill getResourceBillsBySerialNumber(@PathVariable("serialNumber") String serialNumber) {
		return resourceBillRepository.findBySerialNumber(serialNumber);
	}

	@PutMapping("/")
	public Boolean addResourceBill(@RequestBody ResourceBill resourceBill) {
		resourceBillRepository.save(resourceBill);
		return true;
	}

	@PostMapping("/{serialNumber}")
	public Boolean updateResourceBill(@PathVariable("serialNumber") String serialNumber, @RequestBody ResourceBill resourceBill) {
		ResourceBill bill = resourceBillRepository.findBySerialNumber(serialNumber);
		bill.setCostPerUnit(resourceBill.getCostPerUnit());
		bill.setLastIndication(resourceBill.getLastIndication());
		bill.setPerson(resourceBill.getPerson());
		bill.setSummary(resourceBill.getSummary());
		resourceBillRepository.save(bill);
		return true;
	}
}
