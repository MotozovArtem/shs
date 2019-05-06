package ru.rienel.shsheadcontroller.service.rest;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rienel.shsheadcontroller.domain.Person;
import ru.rienel.shsheadcontroller.repository.PersonRepository;

@RestController
@RequestMapping("api/v1/person")
public class PersonService {
	private PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@GetMapping("all")
	public List<Person> getAllPerson() {
		Iterable<Person> queryResult = personRepository.findAll();
		List<Person> persons = new LinkedList<>();
		for (Person person : queryResult) {
			persons.add(person);
		}
		return persons;
	}
}
