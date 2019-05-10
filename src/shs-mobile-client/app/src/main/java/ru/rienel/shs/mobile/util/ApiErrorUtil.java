package ru.rienel.shs.mobile.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiErrorUtil {

	private Integer statusCode;
	private String message;

	public ApiErrorUtil() {
	}

	public Integer status() {
		return statusCode;
	}

	public String message() {
		return message;
	}

	public static ApiErrorUtil parse(Response<?> response) {
		Converter<ResponseBody, ApiErrorUtil> converter = new Retrofit.Builder()
				.build()
				.responseBodyConverter(ApiErrorUtil.class, new Annotation[0]);
		ApiErrorUtil error;
		try {
			error = converter.convert(response.errorBody());
		} catch (IOException e) {
			return new ApiErrorUtil();
		}
		return error;
	}
}