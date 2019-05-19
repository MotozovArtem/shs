package ru.rienel.shs.headcontroller;

import java.util.Properties;
import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import ru.rienel.shs.headcontroller.config.ConvertersConfiguration;
import ru.rienel.shs.headcontroller.config.DatabaseProperties;
import ru.rienel.shs.headcontroller.domain.CustomUser;
import ru.rienel.shs.headcontroller.domain.Person;
import ru.rienel.shs.headcontroller.repository.CustomUserRepository;
import ru.rienel.shs.headcontroller.repository.PersonRepository;

@SpringBootApplication
@EnableConfigurationProperties(DatabaseProperties.class)
public class App {

	@Autowired
	DatabaseProperties databaseProperties;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(databaseProperties.getDriverClassName());
		dataSource.setUrl(databaseProperties.getUrl());
		dataSource.setUsername(databaseProperties.getUsername());
		dataSource.setPassword(databaseProperties.getPassword());
		dataSource.setConnectionProperties(getConnectionProperties());
		return dataSource;
	}

	private Properties getConnectionProperties() {
		Properties properties = new Properties();
		properties.put("cache", "shared");
		return properties;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan(databaseProperties.getDomainPackage());
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setJpaProperties(additionalProperties());
		return entityManagerFactory;
	}

	private Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		if (databaseProperties.getHibernate().getDialect() != null) {
			hibernateProperties.setProperty("hibernate.dialect", databaseProperties.getHibernate().getDialect());
		}
		if (databaseProperties.getHibernate().getShowSql() != null) {
			hibernateProperties.setProperty("hibernate.show_sql", databaseProperties.getHibernate().getShowSql().toString());
		}
		return hibernateProperties;
	}

	@Bean
	public InitializingBean testPerson(PersonRepository personRepository) {
		return () -> {
			Person person = new Person();
			String name = "Artyom";
			String surname = "Motozov";
			String patronymic = "Vladimirovich";
			person.setName(name);
			person.setSurname(surname);
			person.setPatronymic(patronymic);
			person.setAddress("Test address");
			if (!personRepository.existsByNameAndSurnameAndPatronymic(name, surname, patronymic)) {
				personRepository.save(person);
			}
		};
	}

	@Bean
	public InitializingBean testUser(CustomUserRepository customUserRepository, PersonRepository personRepository) {
		return () -> {
			Person person = personRepository.findByNameAndSurnameAndPatronymic("Artyom",
					"Motozov", "Vladimirovich");
			CustomUser user = new CustomUser();
			user.setUsername("admin");
			user.setPassword("admin");
			user.setPerson(person);
			if (!customUserRepository.existsByUsername("admin")) {
				customUserRepository.save(user);
			}
		};
	}
}
