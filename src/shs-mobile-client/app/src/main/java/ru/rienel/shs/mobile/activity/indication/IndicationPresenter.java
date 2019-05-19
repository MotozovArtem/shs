package ru.rienel.shs.mobile.activity.indication;

import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import ru.rienel.shs.mobile.domain.IndicationRecord;
import ru.rienel.shs.mobile.headcontroller.IndicationRecordApiClient;

public class IndicationPresenter implements IndicationContract.Presenter {

	private IndicationContract.View indicationView;

	private IndicationRecordApiClient apiClient;

	private final List<EventListener> listeners = new CopyOnWriteArrayList<>();

	public IndicationPresenter(IndicationContract.View indicationView) {
		this.indicationView = indicationView;
	}

	@Override
	public void start() {

	}

	@Override
	public void loadData() {

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
