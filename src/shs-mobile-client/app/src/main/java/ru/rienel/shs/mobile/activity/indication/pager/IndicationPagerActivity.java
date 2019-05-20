package ru.rienel.shs.mobile.activity.indication.pager;

import java.sql.SQLException;
import java.util.List;

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

public class IndicationPagerActivity extends FragmentActivity implements IndicationPagerContract.View {

	private static final String TAG = IndicationPagerActivity.class.getName();

	private ViewPager indicationPager;

	private List<IndicationRecord> indicationRecords;

	private Dao<IndicationRecord, Long> indicationRecordRepository;

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

		FragmentManager fragmentManager = getSupportFragmentManager();
		indicationPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
			@Override
			public Fragment getItem(int i) {
				return null;
			}

			@Override
			public int getCount() {
				return 0;
			}
		});
	}

	@Override
	public void setPresenter(IndicationPagerContract.Presenter presenter) {

	}
}
