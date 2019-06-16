package ru.rienel.shs.mobile.activity.meter;

import ru.rienel.shs.mobile.activity.BasePresenter;
import ru.rienel.shs.mobile.activity.BaseView;
import ru.rienel.shs.mobile.domain.ResourceType;
import ru.rienel.shs.mobile.util.HaveListeners;
import ru.rienel.shs.mobile.util.Toastable;

public interface MeterContract {
	interface View extends BaseView<Presenter>, Toastable {
		void setRefreshing(boolean refreshing);
	}

	interface Presenter extends BasePresenter, HaveListeners {

		void loadData();

		void addResourceMeter(String serialNumber, ResourceType resourceType);
	}
}
