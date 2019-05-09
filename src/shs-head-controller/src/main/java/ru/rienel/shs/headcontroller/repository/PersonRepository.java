package ru.rienel.shs.headcontroller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ru.rienel.shs.headcontroller.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
	List<Person> findByName(String name);

	List<Person> findBySurname(String surname);

	Person findByNameAndSurnameAndPatronymic(String name, String surname, String patronymic);

	boolean existsByNameAndSurnameAndPatronymic(String name, String surname, String patronymic);
}
