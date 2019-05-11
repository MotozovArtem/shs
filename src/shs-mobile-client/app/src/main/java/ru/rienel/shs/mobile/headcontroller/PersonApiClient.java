package ru.rienel.shs.mobile.headcontroller;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;

import ru.rienel.shs.mobile.domain.dto.PersonDto;

public interface PersonApiClient {
	@GET("api/v1/person/all")
	List<PersonDto> getAllPersons();

	@GET("api/v1/person/{id}")
	List<PersonDto> getPersonById(@Path("id") Long personId);
}
