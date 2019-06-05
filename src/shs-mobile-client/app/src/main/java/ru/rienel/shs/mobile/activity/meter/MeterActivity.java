package ru.rienel.shs.mobile.activity.meter;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.db.DatabaseHelper;

public class MeterActivity extends AppCompatActivity {

	private MeterRootFragment meterRootFragment;

	private MeterContract.Presenter resourceMeterPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meter_activity);

		FragmentManager fragmentManager = getSupportFragmentManager();
		meterRootFragment = (MeterRootFragment)fragmentManager.findFragmentById(R.id.resourceMeterRootFragmentContainer);
		if (meterRootFragment == null) {
			meterRootFragment = MeterRootFragment.getInstance();
			fragmentManager.beginTransaction()
					.add(R.id.resourceMeterRootFragmentContainer, meterRootFragment)
					.commit();
		}
		DatabaseHelper dbHelper = new DatabaseHelper(this);
		resourceMeterPresenter = new MeterPresenter(dbHelper, meterRootFragment,
				PreferenceManager.getDefaultSharedPreferences(this));
		meterRootFragment.setPresenter(resourceMeterPresenter);
	}
}
