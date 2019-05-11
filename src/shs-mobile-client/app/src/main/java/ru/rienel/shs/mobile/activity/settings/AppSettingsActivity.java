package ru.rienel.shs.mobile.activity.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.rienel.shs.mobile.R;

public class AppSettingsActivity extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_activity);
		getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.settingsContainer, new AppSettingsFragment())
				.commit();
	}
}
