package ru.rienel.shs.mobile.activity.person;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.config.AppConfig;
import ru.rienel.shs.mobile.domain.Person;
import ru.rienel.shs.mobile.domain.converter.PersonConverter;
import ru.rienel.shs.mobile.domain.dto.PersonDto;
import ru.rienel.shs.mobile.headcontroller.PersonApi;
import ru.rienel.shs.mobile.headcontroller.PersonApiClient;

public class PersonPresenter implements PersonContract.Presenter {
	private static final String TAG = PersonPresenter.class.getName();

	private final List<EventListener> listeners = new CopyOnWriteArrayList<>();

	private PersonContract.View personRootView;
	private PersonApiClient apiClient;

	public PersonPresenter(PersonContract.View personRootView) {
		this.personRootView = personRootView;
		apiClient = PersonApi.newClient(AppConfig.HEAD_CONTROLLER_URL, "aaa");
	}

	@Override
	public void start() {

	}

	@Override
	public void loadData() {
		Call<List<PersonDto>> request = apiClient.getAllPersons();
		request.enqueue(new Callback<List<PersonDto>>() {
			@Override
			@EverythingIsNonNull
			public void onResponse(Call<List<PersonDto>> call, Response<List<PersonDto>> response) {
				if (!response.isSuccessful()) {
					personRootView.makeShortToastWithText("CODE: " + response.code());
				} else {
					List<PersonDto> result = response.body();
					if (result != null) {
						List<Person> persons = new ArrayList<>(result.size());
						for (PersonDto dto : result) {
							persons.add(PersonConverter.fromDto(dto));
						}
						fireResponse(persons);
					}
				}
				personRootView.setRefreshing(false);
			}

			@Override
			@EverythingIsNonNull
			public void onFailure(Call<List<PersonDto>> call, Throwable e) {
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

	/*
	*	class ScanListener implements FlightImageScanner.FlightImageListener {
		private final UsbDevice device;

		public ScanListener(UsbDevice device) {
			Objects.requireNonNull(device);
			this.device = device;
		}

		@Override
		public void scanCompleted(FlightImageScanner.ScanEvent event) {
			devicesModel.scanCompleted(device);
			uploadController.submitCurrentProjects();
		}

		@Override
		public void scanTerminated(FlightImageScanner.ScanEvent event) {
			devicesModel.scanTerminated(device);
		}

		@Override
		public void flightImageFound(FlightImageScanner.FlightImageEvent event) {
			projectsModel.addFlightImage(event.getImagePath(), event.getFlightId());
		}
	}
	 */
}
