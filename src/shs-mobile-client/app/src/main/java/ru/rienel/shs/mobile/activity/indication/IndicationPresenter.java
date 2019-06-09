package ru.rienel.shs.mobile.activity.indication;

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
import ru.rienel.shs.mobile.domain.IndicationRecord;
import ru.rienel.shs.mobile.domain.ResourceMeter;
import ru.rienel.shs.mobile.headcontroller.IndicationRecordApi;
import ru.rienel.shs.mobile.headcontroller.IndicationRecordApiClient;

public class IndicationPresenter implements IndicationContract.Presenter {

	private static final String TAG = IndicationPresenter.class.getName();

	private IndicationContract.View indicationView;

	private IndicationRecordApiClient apiClient;

	private DatabaseHelper dbHelper;

	private final List<EventListener> listeners = new CopyOnWriteArrayList<>();

	public IndicationPresenter(IndicationContract.View indicationView, SharedPreferences sharedPreferences,
	                           DatabaseHelper dbHelper) {
		this.indicationView = indicationView;
		String url = sharedPreferences.getString("pref_hc_address", AppConfig.HEAD_CONTROLLER_URL);
		String port = sharedPreferences.getString("pref_hc_port", Integer.toString(AppConfig.HEAD_CONTROLLER_PORT));
		apiClient = IndicationRecordApi.newClient("http://" + url + ":" + port, "aaa");
		this.dbHelper = dbHelper;
	}

	@Override
	public void loadData() {
		Call<List<IndicationRecord>> request = apiClient.getAllIndicationRecords();
		request.enqueue(new Callback<List<IndicationRecord>>() {
			@Override
			@EverythingIsNonNull
			public void onResponse(Call<List<IndicationRecord>> call, Response<List<IndicationRecord>> response) {
				if (!response.isSuccessful()) {
					indicationView.makeShortToastWithText("CODE: " + response.code());
				} else {
					List<IndicationRecord> result = response.body();
					if (result != null) {
						fireResponse(result);
						saveResponse(result);
					}
				}
				indicationView.setRefreshing(false);
			}

			@Override
			@EverythingIsNonNull
			public void onFailure(Call<List<IndicationRecord>> call, Throwable e) {
				Log.e(TAG, "Exception while requesting:", e);
				indicationView.makeShortToast(R.string.hcNotAvailable);
				indicationView.setRefreshing(false);
			}
		});
	}

	private void saveResponse(List<IndicationRecord> result) {
		Dao<IndicationRecord, Long> indicationRecordRepository = null;
		Dao<ResourceMeter, Long> resourceMeterRepository = null;
		try {
			indicationRecordRepository = dbHelper.getIndicationRecordDao();
			resourceMeterRepository = dbHelper.getResourceMeterDao();
		} catch (SQLException e) {
			Log.e(TAG, "Cannot instantiate repository", e);
			return;
		}

		for (IndicationRecord record : result) {
			try {
				resourceMeterRepository.createOrUpdate(record.getDevice());
				indicationRecordRepository.createOrUpdate(record);
			} catch (SQLException e) {
				Log.e(TAG, "Cannot save indication record", e);
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

	private void fireResponse(List<IndicationRecord> indicationRecords) {
		IndicationApiResponseEvent event = new IndicationApiResponseEvent(this, indicationRecords);
		for (EventListener listener : listeners) {
			if (listener instanceof IndicationApiResponseListener) {
				try {
					((IndicationApiResponseListener)listener).response(event);
				} catch (Exception e) {
					Log.e(TAG, "Listener error:", e);
				}
			}
		}
	}

	public class IndicationApiResponseEvent extends EventObject {
		private final List<IndicationRecord> indicationRecordList;

		public IndicationApiResponseEvent(Object source, List<IndicationRecord> indicationRecordList) {
			super(source);
			this.indicationRecordList = indicationRecordList;
		}

		public List<IndicationRecord> getIndicationRecordList() {
			return indicationRecordList;
		}
	}

	public interface IndicationApiResponseListener extends EventListener {

		void response(IndicationApiResponseEvent event);
	}
}
