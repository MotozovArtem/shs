package ru.rienel.shs.mobile.activity.indication;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.db.DatabaseHelper;

public class IndicationActivity extends AppCompatActivity {

	private static final String TAG = IndicationActivity.class.getName();

	private IndicationRootFragment indicationRootFragment;

	private IndicationPresenter presenter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.indication_activity);
		DatabaseHelper dbHelper = new DatabaseHelper(this);

		FragmentManager fragmentManager = getSupportFragmentManager();
		indicationRootFragment =
				(IndicationRootFragment)fragmentManager.findFragmentById(R.id.indicationRootFragmentContainer);
		if (indicationRootFragment == null) {
			indicationRootFragment = IndicationRootFragment.getInstance();
			fragmentManager.beginTransaction()
					.add(R.id.indicationRootFragmentContainer, indicationRootFragment)
					.commit();
		}
		presenter = new IndicationPresenter(indicationRootFragment,
				PreferenceManager.getDefaultSharedPreferences(this),
				dbHelper);
		indicationRootFragment.setPresenter(presenter);
	}

	@Override
	public boolean onSupportNavigateUp() {
		onBackPressed();
		return true;
	}
}
