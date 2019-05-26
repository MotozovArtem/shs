package ru.rienel.shs.mobile.headcontroller;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import ru.rienel.shs.mobile.domain.ResourceMeter;

public interface ResourceMeterApiClient {
	String BASE_URL = "/api/v1/meter";

	@GET(BASE_URL + "/")
	Call<List<ResourceMeter>> getAllResourceMeters();

	@GET(BASE_URL + "/{serialNumber}")
	Call<ResourceMeter> getResourceMeterBySerialNumber(@Path("serialNumber") String serialNumber);

	@PUT(BASE_URL + "/")
	Call<Boolean> addResourceMeter(@Body ResourceMeter resourceMeter);

	@POST(BASE_URL + "/{serialNumber}")
	Call<Boolean> updateResourceMeter(@Body ResourceMeter resourceMeter);

	@DELETE(BASE_URL + "/{serialNumber}")
	Call<Boolean> deleteResourceMeterBySerialNumber(@Path("serialNumber") String serialNumber);
}
