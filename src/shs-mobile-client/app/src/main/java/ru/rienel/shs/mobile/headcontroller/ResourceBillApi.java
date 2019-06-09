package ru.rienel.shs.mobile.headcontroller;

import java.io.IOException;

import android.util.Log;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class ResourceBillApi {
	private static final String TAG = ResourceBillApi.class.getName();

	private ResourceBillApi() {
		// Do nothing
	}

	public static ResourceBillApiClient newClient(String url, String authorizationToken) {
		final OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.addInterceptor(new ResourceBillInterceptor(authorizationToken))
				.build();

		return new Retrofit.Builder()
				.client(okHttpClient)
				.addConverterFactory(JacksonConverterFactory.create())
				.baseUrl(url)
				.build()
				.create(ResourceBillApiClient.class);
	}

	private static class ResourceBillInterceptor implements Interceptor {

		private final String authorizationToken;

		public ResourceBillInterceptor(String authorizationToken) {
			this.authorizationToken = authorizationToken;
		}

		@Override
		@EverythingIsNonNull
		public Response intercept(Chain chain) throws IOException {
			Request request = chain.request();
			request = request.newBuilder()
					.addHeader("Authorization", authorizationToken)
					.build();
			Log.d(TAG,
					String.format("\n-----> HTTP %s %s\nHeaders:\n%s \nBody:%s\n------>",
							request.method(), request.url(), request.headers(), request.body()
					));
			Response response = chain.proceed(request);
			Log.d(TAG, String.format("\n<----- HTTP %s \n Headers:\n%s \nBody:%s\n<-----",
					response.code(), response.headers(), response.body()));
			return response;
		}
	}
}
