package ru.rienel.shs.mobile.activity.meter;

import java.sql.SQLException;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import android.content.SharedPreferences;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.config.AppConfig;
import ru.rienel.shs.mobile.db.DatabaseHelper;
import ru.rienel.shs.mobile.domain.ResourceMeter;
import ru.rienel.shs.mobile.headcontroller.ResourceMeterApi;
import ru.rienel.shs.mobile.headcontroller.ResourceMeterApiClient;

public class MeterPresenter implements MeterContract.Presenter {

	private static final String TAG = MeterPresenter.class.getName();

	private final DatabaseHelper dbHelper;

	private Dao<ResourceMeter, Long> resourceMeterRepository;

	private MeterContract.View resourceMeterView;

	private ResourceMeterApiClient apiClient;

	private List<EventListener> listeners = new CopyOnWriteArrayList<>();

	public MeterPresenter(DatabaseHelper dbHelper, MeterContract.View resourceMeterView, SharedPreferences sharedPreferences) {
		this.dbHelper = dbHelper;
		try {
			resourceMeterRepository = dbHelper.getResourceMeterDao();
		} catch (SQLException e) {
			Log.e(TAG, "Cannot instantiate Resource Meter repository", e);
		}
		this.resourceMeterView = resourceMeterView;
		String url = sharedPreferences.getString("pref_hc_address", AppConfig.HEAD_CONTROLLER_URL);
		String port = sharedPreferences.getString("pref_hc_port", Integer.toString(AppConfig.HEAD_CONTROLLER_PORT));
		this.apiClient = ResourceMeterApi.newClient("http://" + url + ":" + port, "aaa");
	}

	@Override
	public void loadData() {
		Call<List<ResourceMeter>> request = apiClient.getAllResourceMeters();
		request.enqueue(new Callback<List<ResourceMeter>>() {
			@Override
			@EverythingIsNonNull
			public void onResponse(Call<List<ResourceMeter>> call, Response<List<ResourceMeter>> response) {
				if (!response.isSuccessful()) {
					resourceMeterView.makeShortToastWithText("CODE: " + response.code());
				} else {
					List<ResourceMeter> result = response.body();
					if (result != null) {
						fireResponse(result);
						saveResponse(result);
					}
				}
				resourceMeterView.setRefreshing(false);
			}

			@Override
			@EverythingIsNonNull
			public void onFailure(Call<List<ResourceMeter>> call, Throwable e) {
				Log.e(TAG, "Exception while requesting", e);
				resourceMeterView.makeShortToast(R.string.hcNotAvailable);
				resourceMeterView.setRefreshing(false);
			}
		});
	}

	private void saveResponse(List<ResourceMeter> resourceMeterList) {
		for (ResourceMeter resourceMeter : resourceMeterList) {
			try {
				resourceMeterRepository.createOrUpdate(resourceMeter);
			} catch (SQLException e) {
				Log.e(TAG, "Cannot save ResourceMeter object to Database", e);
			}
		}
	}

	@Override
	public void start() {

	}

	public void fireResponse(List<ResourceMeter> resourceMeters) {
		MeterApiResponseEvent event = new MeterApiResponseEvent(this, resourceMeters, true);
		for (EventListener listener : listeners) {
			if (listener instanceof MeterApiResponseListener) {
				try {
					((MeterApiResponseListener)listener).response(event);
				} catch (Exception e) {
					Log.e(TAG, "Listener error: ", e);
				}
			}

		}
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

	public static class MeterApiResponseEvent extends EventObject {
		private List<ResourceMeter> resourceMeters;
		private Boolean success;

		public MeterApiResponseEvent(Object source, List<ResourceMeter> resourceMeters, Boolean success) {
			super(source);
			this.resourceMeters = resourceMeters;
			this.success = success;
		}

		public List<ResourceMeter> getResourceMeters() {
			return resourceMeters;
		}

		public Boolean getSuccess() {
			return success;
		}
	}

	public interface MeterApiResponseListener extends EventListener {
		void response(MeterApiResponseEvent event);
	}
}
