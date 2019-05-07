package ru.rienel.shsheadcontroller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ru.rienel.shsheadcontroller.domain.ResourceBill;

public interface ResourceBillRepository extends CrudRepository<ResourceBill, Long> {
	List<ResourceBill> findBySerialNumber(String serialNumber);
}
