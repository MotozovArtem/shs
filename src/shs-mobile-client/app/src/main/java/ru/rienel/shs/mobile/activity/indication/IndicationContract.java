package ru.rienel.shs.mobile.activity.indication;

import ru.rienel.shs.mobile.activity.BasePresenter;
import ru.rienel.shs.mobile.activity.BaseView;
import ru.rienel.shs.mobile.util.HaveListeners;
import ru.rienel.shs.mobile.util.Toastable;

public interface IndicationContract {
	interface View extends BaseView<Presenter>, Toastable {

	}

	interface Presenter extends BasePresenter, HaveListeners {

		void loadData();
	}
}
