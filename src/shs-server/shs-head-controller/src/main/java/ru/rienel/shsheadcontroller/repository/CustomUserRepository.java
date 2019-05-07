package ru.rienel.shsheadcontroller.repository;

import org.springframework.data.repository.CrudRepository;

import ru.rienel.shsheadcontroller.domain.CustomUser;

public interface CustomUserRepository extends CrudRepository<CustomUser, Long> {

	CustomUser findByUsername(String username);
}
