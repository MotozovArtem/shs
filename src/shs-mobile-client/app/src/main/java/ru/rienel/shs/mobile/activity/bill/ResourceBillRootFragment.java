package ru.rienel.shs.mobile.activity.bill;

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

public class ResourceBillRootFragment extends Fragment implements ResourceBillContract.View, SwipeRefreshLayout.OnRefreshListener {

	private ResourceBillContract.Presenter resourceBillPresenter;

	private SwipeRefreshLayout swipeRefreshLayout;

	private ResourceBillListFragment resourceBillListFragment;

	public static ResourceBillRootFragment getInstance() {
		ResourceBillRootFragment fragment = new ResourceBillRootFragment();
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.resource_bill_root_fragment, container, false);
		swipeRefreshLayout = view.findViewById(R.id.resourceBillSwipeRefreshLayout);
		swipeRefreshLayout.setOnRefreshListener(this);
		swipeRefreshLayout.setColorSchemeResources(
				android.R.color.holo_red_light,
				android.R.color.holo_blue_light,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light);

		swipeRefreshLayout.post(() -> {
			swipeRefreshLayout.setRefreshing(true);
			resourceBillPresenter.loadData();
		});

		setHasOptionsMenu(true);


		((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
		((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		FragmentManager fragmentManager = getFragmentManager();
		resourceBillListFragment = (ResourceBillListFragment)fragmentManager.findFragmentById(R.id.resourceBillSwipeRefreshLayout);
		if (resourceBillListFragment == null) {
			resourceBillListFragment = ResourceBillListFragment.getInstance(resourceBillPresenter);
			fragmentManager.beginTransaction()
					.add(R.id.resourceBillSwipeRefreshLayout, resourceBillListFragment)
					.commit();
		}
		return view;
	}

	@Override
	public void setPresenter(ResourceBillContract.Presenter presenter) {
		resourceBillPresenter = presenter;
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		swipeRefreshLayout.setRefreshing(refreshing);
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
	public void onRefresh() {
		resourceBillPresenter.loadData();
	}
}
