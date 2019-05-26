package ru.rienel.shs.mobile.headcontroller;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import ru.rienel.shs.mobile.domain.ResourceBill;

public interface ResourceBillApiClient {
	String BASE_URL = "/api/v1/bill";

	@GET(BASE_URL + "/")
	Call<List<ResourceBill>> getAllBills();

	@GET(BASE_URL + "/{serialNumber}")
	Call<ResourceBill> getBillBySerialNumber(@Path("serialNumber") String serialNumber);

	@PUT(BASE_URL + "/")
	Call<Boolean> addResourceBill(@Body ResourceBill resourceBill);

	@POST(BASE_URL + "/{serialNumber}")
	Call<Boolean> updateResourceBill(@Body ResourceBill resourceBill);
}
