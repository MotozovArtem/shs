package ru.rienel.shs.mobile.activity.person;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.rienel.shs.mobile.R;

public class PersonActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_activity);

		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = fragmentManager.findFragmentById(R.id.personRootFragmentContainer);
		if (fragment == null) {
			fragment = new PersonRootFragment();
			fragmentManager.beginTransaction()
					.add(R.id.personRootFragmentContainer, fragment)
					.commit();
		}
	}
}
