package ru.rienel.shs.mobile.activity.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserRootFragment extends Fragment implements UserContract.View {

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(null, container, false);
		return view;
	}

	@Override
	public void setPresenter(UserContract.Presenter presenter) {

	}
}
