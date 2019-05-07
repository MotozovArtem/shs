package ru.rienel.shsheadcontroller.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.rienel.shsheadcontroller.config.DatabaseProperties;
import ru.rienel.shsheadcontroller.domain.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties(DatabaseProperties.class)
public class PersonRepositoryTest {
	@Autowired
	private PersonRepository personRepository;

	@Test
	public void testAddPerson(){
		Person person = new Person();
		person.setName("Artyom");
		person.setSurname("Motozov");
		person.setPatronymic("Vladimirovich");
		person.setAddress("Address");

		Person actualPerson = personRepository.save(person);
		Assert.assertEquals(1, personRepository.count());
		Assert.assertEquals(person.getName(), actualPerson.getName());
		Assert.assertEquals(person.getSurname(), actualPerson.getSurname());
		Assert.assertEquals(person.getPatronymic(), actualPerson.getPatronymic());
		Assert.assertEquals(person.getAddress(), actualPerson.getPatronymic());

		personRepository.deleteAll();
	}
}
