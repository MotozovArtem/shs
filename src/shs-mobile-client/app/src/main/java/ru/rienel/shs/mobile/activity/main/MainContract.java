package ru.rienel.shs.mobile.activity.main;

import ru.rienel.shs.mobile.activity.BasePresenter;
import ru.rienel.shs.mobile.activity.BaseView;
import ru.rienel.shs.mobile.util.HaveListeners;
import ru.rienel.shs.mobile.util.Toastable;

public interface MainContract {
	interface View extends BaseView<Presenter>, Toastable {

		void setRefreshing(boolean refreshing);

	}

	interface Presenter extends BasePresenter, HaveListeners {

		void loadData();

	}
}
