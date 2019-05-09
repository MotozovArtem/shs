package ru.rienel.shs.headcontroller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ru.rienel.shs.headcontroller.domain.ResourceMeter;

public interface ResourceMeterRepository extends CrudRepository<ResourceMeter, Long> {
	List<ResourceMeter> findBySerialNumber(String serialNumber);
}
