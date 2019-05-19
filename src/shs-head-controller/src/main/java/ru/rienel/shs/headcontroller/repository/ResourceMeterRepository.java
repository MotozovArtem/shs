package ru.rienel.shs.headcontroller.repository;

import org.springframework.data.repository.CrudRepository;

import ru.rienel.shs.headcontroller.domain.ResourceMeter;

public interface ResourceMeterRepository extends CrudRepository<ResourceMeter, Long> {
	ResourceMeter findBySerialNumber(String serialNumber);
}
