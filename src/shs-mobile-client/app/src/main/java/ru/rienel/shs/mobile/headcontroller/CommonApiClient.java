package ru.rienel.shs.mobile.headcontroller;

import retrofit2.Call;
import retrofit2.http.GET;

import ru.rienel.shs.mobile.domain.Common;

public interface CommonApiClient {
	String BASE_URL = "/api/v1";

	@GET(BASE_URL + "/")
	Call<Common> getCommon();
}
