package ru.rienel.shs.mobile.activity.bill;

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
import ru.rienel.shs.mobile.domain.ResourceBill;
import ru.rienel.shs.mobile.headcontroller.ResourceBillApi;
import ru.rienel.shs.mobile.headcontroller.ResourceBillApiClient;

public class ResourceBillPresenter implements ResourceBillContract.Presenter {

	private static final String TAG = ResourceBillPresenter.class.getName();

	private final List<EventListener> listeners = new CopyOnWriteArrayList<>();

	private ResourceBillContract.View billView;

	private DatabaseHelper dbHelper;

	private ResourceBillApiClient apiClient;

	public ResourceBillPresenter(ResourceBillContract.View billView, SharedPreferences sharedPreferences, DatabaseHelper dbHelper) {
		this.billView = billView;
		String url = sharedPreferences.getString("pref_hc_address", AppConfig.HEAD_CONTROLLER_URL);
		String port = sharedPreferences.getString("pref_hc_port", Integer.toString(AppConfig.HEAD_CONTROLLER_PORT));
		apiClient = ResourceBillApi.newClient("http://" + url + ":" + port, AppConfig.AUTHORIZATION_TOKEN);
		this.dbHelper = dbHelper;
	}


	@Override
	public void loadData() {
		Call<List<ResourceBill>> request = apiClient.getAllBills();
		request.enqueue(new Callback<List<ResourceBill>>() {

			@Override
			@EverythingIsNonNull
			public void onResponse(Call<List<ResourceBill>> call, Response<List<ResourceBill>> response) {
				if (!response.isSuccessful()) {
					billView.makeShortToastWithText("CODE: " + response.code());
				} else {
					List<ResourceBill> result = response.body();
					if (result != null) {
						fireResponse(result);
						saveResponse(result);
					}
				}
				billView.setRefreshing(false);
			}

			@Override
			@EverythingIsNonNull
			public void onFailure(Call<List<ResourceBill>> call, Throwable e) {
				Log.e(TAG, "Exception while requesting:", e);
				billView.makeShortToast(R.string.hcNotAvailable);
				billView.setRefreshing(false);
			}
		});
	}

	private void saveResponse(List<ResourceBill> result) {
		Dao<ResourceBill, Long> resourceBillRepository = null;
		try {
			resourceBillRepository = dbHelper.getResourceBillDao();
		} catch (SQLException e) {
			Log.e(TAG, "Cannot instantiate repository", e);
			return;
		}

		for (ResourceBill resourceBill : result) {
			try {
				resourceBillRepository.createOrUpdate(resourceBill);
			} catch (SQLException e) {
				Log.e(TAG, "Cannot save indication record", e);
			}
		}
	}

	////////////
	// Events //
	////////////
	private void fireResponse(List<ResourceBill> result) {
		BillApiResponseEvent event = new BillApiResponseEvent(this, result);
		for (EventListener listener : listeners) {
			if (listener instanceof BillApiResponseListener) {
				try {
					((BillApiResponseListener)listener).response(event);
				} catch (Exception e) {
					Log.e(TAG, "Listener error:", e);
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
		listeners.add(listener);
	}

	public class BillApiResponseEvent extends EventObject {

		private List<ResourceBill> resourceBills;

		public BillApiResponseEvent(Object source, List<ResourceBill> resourceBills) {
			super(source);
			this.resourceBills = resourceBills;
		}

		public List<ResourceBill> getResourceBills() {
			return resourceBills;
		}
	}


	public interface BillApiResponseListener extends EventListener {

		void response(ResourceBillPresenter.BillApiResponseEvent event);

	}
}
