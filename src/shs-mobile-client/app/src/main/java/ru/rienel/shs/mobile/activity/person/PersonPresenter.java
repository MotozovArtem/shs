package ru.rienel.shs.mobile.activity.person;

import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import android.content.SharedPreferences;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.config.AppConfig;
import ru.rienel.shs.mobile.domain.Person;
import ru.rienel.shs.mobile.headcontroller.PersonApi;
import ru.rienel.shs.mobile.headcontroller.PersonApiClient;

public class PersonPresenter implements PersonContract.Presenter {
	private static final String TAG = PersonPresenter.class.getName();

	private final List<EventListener> listeners = new CopyOnWriteArrayList<>();

	private PersonContract.View personRootView;

	private PersonApiClient apiClient;

	private SharedPreferences sharedPreferences;

	public PersonPresenter(PersonContract.View personRootView, SharedPreferences sharedPreferences) {
		this.personRootView = personRootView;
		this.sharedPreferences = sharedPreferences;
		String url = sharedPreferences.getString("pref_hc_address", AppConfig.HEAD_CONTROLLER_URL);
		String port = sharedPreferences.getString("pref_hc_port", Integer.toString(AppConfig.HEAD_CONTROLLER_PORT));
		apiClient = PersonApi.newClient("http://" + url + ":" + port, "aaa");
	}

	@Override
	public void loadData() {
		Call<List<Person>> request = apiClient.getAllPersons();
		request.enqueue(new Callback<List<Person>>() {
			@Override
			@EverythingIsNonNull
			public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
				if (!response.isSuccessful()) {
					personRootView.makeShortToastWithText("CODE: " + response.code());
				} else {
					List<Person> result = response.body();
					if (result != null) {
						fireResponse(result);
					}
				}
				personRootView.setRefreshing(false);
			}

			@Override
			@EverythingIsNonNull
			public void onFailure(Call<List<Person>> call, Throwable e) {
				Log.e(TAG, "Exception while requesting:", e);
				personRootView.makeShortToast(R.string.hcNotAvailable);
				personRootView.setRefreshing(false);
			}
		});
	}

	@Override
	public void addListener(EventListener listener) {
		Objects.requireNonNull(listener);
		listeners.add(listener);
	}

	@Override
	public void removeListener(EventListener listener) {
		Objects.requireNonNull(listener);
		listeners.remove(listener);
	}

	private void fireResponse(List<Person> personList) {
		PersonApiResponseEvent event = new PersonApiResponseEvent(this, personList);
		for (EventListener listener : listeners) {
			if (listener instanceof PersonApiResponseListener) {
				try {
					((PersonApiResponseListener)listener).response(event);
				} catch (Exception e) {
					Log.e(TAG, "Listener error:", e);
				}
			}
		}
	}

	public static class PersonApiResponseEvent extends EventObject {
		private List<Person> personList;

		public PersonApiResponseEvent(Object source, List<Person> personList) {
			super(source);
			this.personList = personList;
		}

		public List<Person> getPersonList() {
			return personList;
		}
	}

	public interface PersonApiResponseListener extends EventListener {

		void response(PersonApiResponseEvent event);

	}
}
