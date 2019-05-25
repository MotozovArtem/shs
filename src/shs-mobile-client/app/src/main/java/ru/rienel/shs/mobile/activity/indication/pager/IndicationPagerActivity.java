package ru.rienel.shs.mobile.activity.indication.pager;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.db.DatabaseHelper;
import ru.rienel.shs.mobile.domain.IndicationRecord;
import ru.rienel.shs.mobile.util.Constants;

public class IndicationPagerActivity extends FragmentActivity {

	private static final String TAG = IndicationPagerActivity.class.getName();

	private ViewPager indicationPager;

	private List<IndicationRecord> indicationRecords;

	private Dao<IndicationRecord, Long> indicationRecordRepository;

	private IndicationPagerContract.Presenter indicationPagerPresenter;

	public static Intent newIntent(Context packageContext, Long indicationId) {
		Intent intent = new Intent(packageContext, IndicationPagerActivity.class);
		intent.putExtra(Constants.INDICATION_RECORD_ID, indicationId);
		return intent;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.indication_pager_activity);
		indicationPager = findViewById(R.id.indicationViewPager);
		DatabaseHelper dbHelper = new DatabaseHelper(this);
		try {
			indicationRecordRepository = dbHelper.getIndicationRecordDao();
		} catch (SQLException e) {
			Log.e(TAG, "Cannot create repository", e);
		}
		try {
			indicationRecords = indicationRecordRepository.queryForAll();
		} catch (SQLException e) {
			Log.e(TAG, "Cannot get all objects from Indication Record repository", e);
		}

		indicationPagerPresenter = new IndicationPagerPresenter(indicationRecordRepository);

		FragmentManager fragmentManager = getSupportFragmentManager();
		indicationPager.setAdapter(newPagerAdapter(fragmentManager));

		Long currentIndicationId = getIntent().getLongExtra(Constants.INDICATION_RECORD_ID, 0);
		for (int i = 0; i < indicationRecords.size(); i++) {
			if (indicationRecords.get(i).getId().equals(currentIndicationId)) {
				indicationPager.setCurrentItem(i);
				break;
			}
		}
	}

	private FragmentPagerAdapter newPagerAdapter(FragmentManager fragmentManager) {
		return new FragmentPagerAdapter(fragmentManager) {
			@Override
			public Fragment getItem(int i) {
				IndicationRecord record = indicationRecords.get(i);
				IndicationFragment fragment = IndicationFragment.getInstance(record.getId());
				fragment.setPresenter(indicationPagerPresenter);
				indicationPagerPresenter.setIndicationPagerView(fragment);
				return fragment;
			}

			@Override
			public int getCount() {
				if (indicationRecords == null) {
					return 0;
				}
				return indicationRecords.size();
			}
		};
	}
}
