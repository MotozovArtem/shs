package ru.rienel.shs.mobile.headcontroller;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import ru.rienel.shs.mobile.domain.Person;

public interface PersonApiClient {
	String BASE_URL = "/api/v1/person";

	@GET(BASE_URL + "/")
	Call<List<Person>> getAllPersons();

	@GET(BASE_URL + "/{id}")
	Call<List<Person>> getPersonById(@Path("id") Long personId);

	@PUT(BASE_URL + "/")
	Call<Boolean> addPerson(@Body Person person);

	@POST(BASE_URL + "/{id}")
	Call<Boolean> updatePerson(@Path("id") Long id, @Body Person person);

	@DELETE(BASE_URL + "/{id}")
	Call<Boolean> deletePerson(@Path("id") Long id);
}
