package ru.rienel.shs.mobile.activity.meter;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.db.DatabaseHelper;

public class MeterActivity extends AppCompatActivity {

	private Toolbar toolbar;

	private MeterRootFragment meterRootFragment;

	private MeterAddFragment meterAddFragment;

	private MeterContract.Presenter resourceMeterPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meter_activity);

		toolbar = findViewById(R.id.resourceMeterToolBar);
		setSupportActionBar(toolbar);

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.toolbar_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
			case R.id.toolbarPlus:
				if (meterAddFragment == null) {
					meterAddFragment = MeterAddFragment.newInstance(resourceMeterPresenter);
					FragmentManager fragmentManager = getSupportFragmentManager();
					fragmentManager.beginTransaction()
							.hide(meterRootFragment)
							.add(R.id.resourceMeterRootFragmentContainer, meterAddFragment)
							.addToBackStack(null)
							.commit();
				}
				return true;
			case android.R.id.home:
				onBackPressed();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
