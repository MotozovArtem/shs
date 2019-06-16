package ru.rienel.shs.mobile.activity.bill;

import ru.rienel.shs.mobile.activity.BasePresenter;
import ru.rienel.shs.mobile.activity.BaseView;
import ru.rienel.shs.mobile.util.HaveListeners;
import ru.rienel.shs.mobile.util.Toastable;

public interface ResourceBillContract {
	interface View extends BaseView<Presenter>, Toastable {

		void setRefreshing(boolean refreshing);
	}

	interface Presenter extends BasePresenter, HaveListeners {
		void loadData();
	}
}
