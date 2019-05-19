package ru.rienel.shs.headcontroller.service.rest;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.rienel.shs.headcontroller.domain.Person;
import ru.rienel.shs.headcontroller.domain.dto.PersonDto;
import ru.rienel.shs.headcontroller.domain.dto.converters.Converter;
import ru.rienel.shs.headcontroller.repository.PersonRepository;

@RestController
@RequestMapping("api/v1/person")
public class PersonRestService {
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private Converter<Person, PersonDto> personDto;

	@GetMapping("/")
	public List<Person> getAllPerson() {
		Iterable<Person> queryResult = personRepository.findAll();
		List<Person> persons = new LinkedList<>();
		for (Person person : queryResult) {
			persons.add(person);
		}
		return persons;
	}

	@GetMapping("/{id}")
	public Person getPerson(@PathVariable("id") Long id) {
		Optional<Person> queryResult = personRepository.findById(id);
		return queryResult.orElse(null);
	}

	@PutMapping("/")
	public Boolean addPerson(@RequestBody Person person) {
		Person savedPerson = personRepository.save(person);
		return savedPerson != null;
	}

	@PostMapping("/{id}")
	public Boolean updatePerson(@PathVariable("id") Long id, @RequestBody Person person) {
		Optional<Person> queryResult = personRepository.findById(id);
		boolean isUpdated = false;
		if (queryResult.isPresent()) {
			Person fromDatabase = queryResult.get();
			fromDatabase.setName(person.getName());
			fromDatabase.setSurname(person.getSurname());
			fromDatabase.setPatronymic(person.getPatronymic());
			fromDatabase.setAddress(person.getAddress());
			personRepository.save(fromDatabase);
			isUpdated = true;
		}
		return isUpdated;
	}

	@DeleteMapping("/{id}")
	public Boolean deletePerson(@PathVariable("id") Long id) {
		personRepository.deleteById(id);
		return true;
	}
}
