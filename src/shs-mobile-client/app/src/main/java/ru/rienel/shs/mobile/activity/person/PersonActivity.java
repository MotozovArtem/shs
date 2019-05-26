package ru.rienel.shs.mobile.activity.person;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.rienel.shs.mobile.R;

public class PersonActivity extends AppCompatActivity {

	private static final String TAG = PersonActivity.class.getName();

	private PersonRootFragment personRootFragment;

	private PersonPresenter personPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_activity);

		FragmentManager fragmentManager = getSupportFragmentManager();
		personRootFragment = (PersonRootFragment)fragmentManager.findFragmentById(R.id.personRootFragmentContainer);
		if (personRootFragment == null) {
			personRootFragment = new PersonRootFragment();
			fragmentManager.beginTransaction()
					.add(R.id.personRootFragmentContainer, personRootFragment)
					.commit();
		}
		personPresenter = new PersonPresenter(personRootFragment,
				PreferenceManager.getDefaultSharedPreferences(this));

		personRootFragment.setPresenter(personPresenter);
	}

	@Override
	public boolean onSupportNavigateUp() {
		onBackPressed();
		return true;
	}
}
