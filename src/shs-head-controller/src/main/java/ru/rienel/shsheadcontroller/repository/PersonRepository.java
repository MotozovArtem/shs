package ru.rienel.shsheadcontroller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ru.rienel.shsheadcontroller.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
	List<Person> findByName(String name);

	List<Person> findBySurname(String surname);

	Person findByNameAndSurnameAndPatronymic(String name, String surname, String patronymic);
}
