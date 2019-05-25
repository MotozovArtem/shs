package ru.rienel.shs.mobile.activity.indication.pager;

import com.j256.ormlite.dao.Dao;

import ru.rienel.shs.mobile.domain.IndicationRecord;

public class IndicationPagerPresenter implements IndicationPagerContract.Presenter {

	private IndicationPagerContract.View indicationPagerView;

	private Dao<IndicationRecord, Long> indicationRecordRepository;

	public IndicationPagerPresenter(Dao<IndicationRecord, Long> indicationRecordRepository) {
		this.indicationRecordRepository = indicationRecordRepository;
	}

	@Override
	public void start() {

	}

	@Override
	public void setIndicationPagerView(IndicationPagerContract.View indicationPagerView) {
		this.indicationPagerView = indicationPagerView;
	}
}
