package ru.rienel.shs.mobile.activity.indication.pager;

import ru.rienel.shs.mobile.activity.BasePresenter;
import ru.rienel.shs.mobile.activity.BaseView;
import ru.rienel.shs.mobile.domain.IndicationRecord;

public interface IndicationPagerContract {
	interface View extends BaseView<Presenter> {

	}

	interface Presenter extends BasePresenter {
		void setIndicationPagerView(View indicationPagerView);

		IndicationRecord getIndicationRecord(Long indicationId);
	}
}
