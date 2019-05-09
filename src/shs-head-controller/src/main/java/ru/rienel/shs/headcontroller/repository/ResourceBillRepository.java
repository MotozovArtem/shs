package ru.rienel.shs.headcontroller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ru.rienel.shs.headcontroller.domain.ResourceBill;

public interface ResourceBillRepository extends CrudRepository<ResourceBill, Long> {
	List<ResourceBill> findBySerialNumber(String serialNumber);
}
