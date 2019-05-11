package ru.rienel.shs.mobile.activity.settings;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import ru.rienel.shs.mobile.R;

public class AppSettingsFragment extends PreferenceFragmentCompat {
	@Override
	public void onCreatePreferences(Bundle bundle, String rootKey) {
		setPreferencesFromResource(R.xml.preferences, rootKey);
	}
}
