package ru.rienel.shs.mobile.activity.meter;

import java.sql.SQLException;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import android.util.Log;

import com.j256.ormlite.dao.Dao;

import ru.rienel.shs.mobile.db.DatabaseHelper;
import ru.rienel.shs.mobile.domain.ResourceMeter;
import ru.rienel.shs.mobile.util.HaveListeners;

public class MeterPresenter implements MeterContract.Presenter, HaveListeners {

	private static final String TAG = MeterPresenter.class.getName();

	private final DatabaseHelper dbHelper;

	private Dao<ResourceMeter, Long> resourceMeterRepository;

	private MeterContract.View resourceMeterView;

	private List<EventListener> listeners = new CopyOnWriteArrayList<>();

	public MeterPresenter(DatabaseHelper dbHelper, MeterContract.View resourceMeterView) {
		this.dbHelper = dbHelper;
		try {
			resourceMeterRepository = dbHelper.getResourceMeterDao();
		} catch (SQLException e) {
			Log.e(TAG, "Cannot instantiate Resource Meter repository", e);
		}
		this.resourceMeterView = resourceMeterView;
	}

	@Override
	public void loadData() {

	}

	@Override
	public void start() {

	}

	public void fireResponse(List<ResourceMeter> resourceMeters) {
		MeterApiResponseEvent event = new MeterApiResponseEvent(this, resourceMeters);
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

		public MeterApiResponseEvent(Object source, List<ResourceMeter> resourceMeters) {
			super(source);
			this.resourceMeters = resourceMeters;
		}

		public List<ResourceMeter> getResourceMeters() {
			return resourceMeters;
		}
	}

	public interface MeterApiResponseListener extends EventListener {
		void response(MeterApiResponseEvent event);
	}
}
