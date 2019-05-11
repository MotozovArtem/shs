package ru.rienel.shs.mobile.activity.person;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.rienel.shs.mobile.R;

public class PersonRootFragment extends Fragment implements PersonContract.View {

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.person_root_fragment, container, false);
		
		return view;
	}

	@Override
	public void setPresenter(PersonContract.Presenter presenter) {

	}
}
