package ru.rienel.shs.mobile.activity.meter;

import ru.rienel.shs.mobile.activity.BasePresenter;
import ru.rienel.shs.mobile.activity.BaseView;

public interface MeterContract {
	interface View extends BaseView<Presenter> {
		void setRefreshing(boolean refreshing);
	}

	interface Presenter extends BasePresenter {

		void loadData();
	}
}
