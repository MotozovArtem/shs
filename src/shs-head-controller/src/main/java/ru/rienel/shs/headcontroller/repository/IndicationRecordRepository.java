package ru.rienel.shs.headcontroller.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import ru.rienel.shs.headcontroller.domain.IndicationRecord;

public interface IndicationRecordRepository extends CrudRepository<IndicationRecord, Long> {
	List<IndicationRecord> findByRecordUuid(UUID uuid);
}
