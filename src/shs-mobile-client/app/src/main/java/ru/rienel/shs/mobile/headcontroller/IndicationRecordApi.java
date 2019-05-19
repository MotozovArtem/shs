package ru.rienel.shs.mobile.headcontroller;

import java.io.IOException;

import android.util.Log;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class IndicationRecordApi {
	private static final String TAG = IndicationRecordApi.class.getName();

	public static IndicationRecordApiClient newClient(String url, String authorizationToken) {
		final OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.addInterceptor(new IndicationRecordInterceptor(authorizationToken))
				.build();

		return new Retrofit.Builder()
				.client(okHttpClient)
				.baseUrl(url)
				.addConverterFactory(JacksonConverterFactory.create())
				.build()
				.create(IndicationRecordApiClient.class);
	}

	private IndicationRecordApi() {
		// Do nothing
	}

	private static class IndicationRecordInterceptor implements Interceptor {

		private final String authorization;

		public IndicationRecordInterceptor(String authorization) {
			this.authorization = authorization;
		}

		@Override
		public Response intercept(Chain chain) throws IOException {
			Request request = chain.request();
			request = request.newBuilder()
					.addHeader("Authorization", authorization)
					.build();
			Log.d(TAG,
					String.format("-----> HTTP %s %s\nHeaders:\n%s \nBody:%s\n------>",
							request.method(), request.url(), request.headers(), request.body()
					));
			Response response = chain.proceed(request);
			Log.d(TAG, String.format("<----- HTTP %s \n Headers:\n%s \nBody:%s\n<-----",
					response.code(), response.headers(), response.body()));
			return response;
		}
	}
}
