package ru.rienel.shs.mobile.activity.indication.pager;

import java.sql.SQLException;

import android.util.Log;

import com.j256.ormlite.dao.Dao;

import ru.rienel.shs.mobile.domain.IndicationRecord;

public class IndicationPagerPresenter implements IndicationPagerContract.Presenter {

	private static final String TAG = IndicationPagerPresenter.class.getName();

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

	@Override
	public IndicationRecord getIndicationRecord(Long indicationId) {
		IndicationRecord indicationRecord = null;
		try {
			indicationRecord = indicationRecordRepository.queryForId(indicationId);
		} catch (SQLException e) {
			Log.e(TAG, "Cannot find indication record with id: " + indicationId, e);
		}
		return indicationRecord;
	}
}
