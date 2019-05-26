package ru.rienel.shs.mobile.activity.meter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.util.Toastable;

public class MeterRootFragment extends Fragment implements MeterContract.View, SwipeRefreshLayout.OnRefreshListener, Toastable {

	private static final String TAG = MeterRootFragment.class.getName();

	private MeterContract.Presenter resourceMeterPresenter;

	private SwipeRefreshLayout swipeRefreshLayout;

	private MeterListFragment meterListFragment;

	public static MeterRootFragment getInstance() {
		MeterRootFragment fragment = new MeterRootFragment();
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.meter_root_fragment, container, false);
		swipeRefreshLayout = view.findViewById(R.id.resourceMeterSwipeRefreshLayout);
		swipeRefreshLayout.setOnRefreshListener(this);
		swipeRefreshLayout.setColorSchemeResources(
				android.R.color.holo_red_light,
				android.R.color.holo_blue_light,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light);

		swipeRefreshLayout.post(() -> {
			swipeRefreshLayout.setRefreshing(true);
			resourceMeterPresenter.loadData();
		});

		setHasOptionsMenu(true);

		((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
		((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		FragmentManager fragmentManager = getFragmentManager();

		meterListFragment = (MeterListFragment)fragmentManager.findFragmentById(R.id.resourceMeterSwipeRefreshLayout);
		if (meterListFragment == null) {
			meterListFragment = MeterListFragment.getInstance(resourceMeterPresenter);
			fragmentManager.beginTransaction()
					.add(R.id.resourceMeterSwipeRefreshLayout, meterListFragment)
					.commit();
		}

		return view;
	}

	@Override
	public void setPresenter(MeterContract.Presenter presenter) {
		this.resourceMeterPresenter = presenter;
	}

	@Override
	public void onRefresh() {
		resourceMeterPresenter.loadData();
	}

	@Override
	public void makeShortToast(int stringResource) {
		Toast.makeText(getContext(), stringResource, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void makeLongToast(int stringResource) {
		Toast.makeText(getContext(), stringResource, Toast.LENGTH_LONG).show();
	}

	@Override
	public void makeShortToastWithText(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		swipeRefreshLayout.setRefreshing(refreshing);
	}
}
