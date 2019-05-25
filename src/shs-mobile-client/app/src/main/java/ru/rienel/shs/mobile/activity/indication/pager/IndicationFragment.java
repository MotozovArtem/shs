package ru.rienel.shs.mobile.activity.indication.pager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.rienel.shs.mobile.R;

public class IndicationFragment extends Fragment implements IndicationPagerContract.View {

	private IndicationPagerContract.Presenter indicationPagerPresenter;

	public static IndicationFragment getInstance(Long indicationId) {
		IndicationFragment fragment = new IndicationFragment();
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.indication_item, container, false);
		return view;
	}

	@Override
	public void setPresenter(IndicationPagerContract.Presenter indicationPagerPresenter) {
		this.indicationPagerPresenter = indicationPagerPresenter;
	}
}
