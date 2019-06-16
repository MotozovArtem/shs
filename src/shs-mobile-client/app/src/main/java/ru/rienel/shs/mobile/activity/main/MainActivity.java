package ru.rienel.shs.mobile.activity.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.rienel.shs.mobile.R;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);


		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = fragmentManager.findFragmentById(R.id.mainRootFragmentContainer);

		if (fragment == null) {
			fragment = MainRootFragment.getInstance();
			fragmentManager.beginTransaction()
					.add(R.id.mainRootFragmentContainer, fragment)
					.commit();
		}
	}
}
