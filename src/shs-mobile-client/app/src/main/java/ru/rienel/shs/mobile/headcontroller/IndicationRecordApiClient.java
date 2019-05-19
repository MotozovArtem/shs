package ru.rienel.shs.mobile.headcontroller;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import ru.rienel.shs.mobile.domain.IndicationRecord;
import ru.rienel.shs.mobile.domain.ResourceType;

public interface IndicationRecordApiClient {
	String BASE_URL = "/api/v1/record";

	@GET(BASE_URL + "/")
	Call<List<IndicationRecord>> getAllIndicationRecords();

	@GET(BASE_URL + "/{type}")
	Call<List<IndicationRecord>> getAllIndicationRecordsByResourceType(@Path("type") ResourceType type);

	@GET(BASE_URL + "/{id}")
	Call<IndicationRecord> getDetailForIndicationRecord(@Path("id") Long id);
}
