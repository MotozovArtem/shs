package ru.rienel.shs.headcontroller.repository;

import org.springframework.data.repository.CrudRepository;

import ru.rienel.shs.headcontroller.domain.CustomUser;

public interface CustomUserRepository extends CrudRepository<CustomUser, Long> {

	CustomUser findByUsername(String username);

	boolean existsByUsername(String username);
}
