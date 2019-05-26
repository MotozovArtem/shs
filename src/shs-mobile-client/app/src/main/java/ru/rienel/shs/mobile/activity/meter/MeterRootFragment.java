package ru.rienel.shs.mobile.activity.meter;

import android.support.v4.app.Fragment;

public class MeterRootFragment extends Fragment implements MeterContract.View {

	public static MeterRootFragment getInstance() {
		MeterRootFragment fragment = new MeterRootFragment();
		return fragment;
	}

	@Override
	public void setPresenter(MeterContract.Presenter presenter) {

	}
}
