package ru.rienel.shs.mobile.activity.person;

import ru.rienel.shs.mobile.activity.BasePresenter;
import ru.rienel.shs.mobile.activity.BaseView;
import ru.rienel.shs.mobile.util.HaveListeners;
import ru.rienel.shs.mobile.util.Toastable;

public interface PersonContract {
	interface View extends BaseView<Presenter>, Toastable {

		void setRefreshing(Boolean isRefreshing);
	}

	interface Presenter extends BasePresenter, HaveListeners {

		void loadData();

	}
}
