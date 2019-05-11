package ru.rienel.shs.mobile.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.rienel.shs.mobile.R;

public class MainFragment extends Fragment implements MainContract.View {

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_activity, container, false);

		return view;
	}

	@Override
	public void setPresenter(MainContract.Presenter presenter) {

	}
}
