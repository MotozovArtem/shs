package ru.rienel.shs.mobile.activity.main;

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
import ru.rienel.shs.mobile.domain.Common;
import ru.rienel.shs.mobile.headcontroller.CommonApi;
import ru.rienel.shs.mobile.headcontroller.CommonApiClient;

public class MainPresenter implements MainContract.Presenter {

	private static final String TAG = MainPresenter.class.getName();

	private final List<EventListener> listeners = new CopyOnWriteArrayList<>();

	private MainContract.View mainView;

	private CommonApiClient apiClient;

	public MainPresenter(MainContract.View mainView, SharedPreferences sharedPreferences) {
		this.mainView = mainView;
		String url = sharedPreferences.getString("pref_hc_address", AppConfig.HEAD_CONTROLLER_URL);
		String port = sharedPreferences.getString("pref_hc_port", Integer.toString(AppConfig.HEAD_CONTROLLER_PORT));
		this.apiClient = CommonApi.newClient("http://" + url + ":" + port, "aaa");
	}

	@Override
	public void loadData() {
		Call<Common> request = apiClient.getCommon();
		request.enqueue(new Callback<Common>() {
			@Override
			@EverythingIsNonNull
			public void onResponse(Call<Common> call, Response<Common> response) {
				if (!response.isSuccessful()) {
					mainView.makeShortToastWithText("CODE: " + response.code());
				} else {
					Common common = response.body();
					if (common != null) {
						fireResponse(common);
					}
				}
				mainView.setRefreshing(false);
			}

			@Override
			@EverythingIsNonNull
			public void onFailure(Call<Common> call, Throwable e) {
				Log.e(TAG, "Exception while requesting:", e);
				mainView.makeShortToast(R.string.hcNotAvailable);
				mainView.setRefreshing(false);
			}
		});
	}

	////////////
	// Events //
	////////////

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

	private void fireResponse(Common common) {
		CommonApiResponseEvent event = new CommonApiResponseEvent(this, common);
		for (EventListener listener : listeners) {
			if (listener instanceof CommonApiResponseListener) {
				try {
					((CommonApiResponseListener)listener).response(event);
				} catch (Exception e) {
					Log.e(TAG, "Listener error: ", e);
				}
			}
		}
	}

	public static class CommonApiResponseEvent extends EventObject {
		private final Common common;

		public CommonApiResponseEvent(Object source, Common common) {
			super(source);
			this.common = common;
		}

		public Common getCommon() {
			return common;
		}
	}

	public interface CommonApiResponseListener extends EventListener {
		void response(CommonApiResponseEvent event);
	}
}
