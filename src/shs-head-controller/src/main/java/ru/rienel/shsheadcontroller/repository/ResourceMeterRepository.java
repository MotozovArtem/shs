package ru.rienel.shsheadcontroller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ru.rienel.shsheadcontroller.domain.ResourceMeter;

public interface ResourceMeterRepository extends CrudRepository<ResourceMeter, Long> {
	List<ResourceMeter> findBySerialNumber(String serialNumber);
}
