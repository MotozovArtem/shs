package ru.rienel.shs.mobile.activity.main;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.rienel.shs.mobile.R;

public class MainActivity extends AppCompatActivity {

	private MainContract.Presenter mainPresenter;

	private MainRootFragment mainRootFragment;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		FragmentManager fragmentManager = getSupportFragmentManager();
		mainRootFragment = (MainRootFragment)fragmentManager.findFragmentById(R.id.mainRootFragmentContainer);

		if (mainRootFragment == null) {
			mainRootFragment = MainRootFragment.getInstance();
			fragmentManager.beginTransaction()
					.add(R.id.mainRootFragmentContainer, mainRootFragment)
					.commit();
		}
		mainPresenter = new MainPresenter(mainRootFragment, PreferenceManager.getDefaultSharedPreferences(this));

		mainRootFragment.setPresenter(mainPresenter);
	}
}
