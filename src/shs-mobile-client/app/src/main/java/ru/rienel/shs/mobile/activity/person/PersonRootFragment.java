package ru.rienel.shs.mobile.activity.person;

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

import ru.rienel.shs.mobile.R;

public class PersonRootFragment extends Fragment implements PersonContract.View, SwipeRefreshLayout.OnRefreshListener {

	private SwipeRefreshLayout swipeRefreshLayout;
	private PersonListFragment personListFragment;
	private PersonContract.Presenter personPresenter;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.person_root_fragment, container, false);
		swipeRefreshLayout = view.findViewById(R.id.personSwipeRefreshLayout);
		swipeRefreshLayout.setOnRefreshListener(this);
		swipeRefreshLayout.setColorSchemeResources(
				android.R.color.holo_red_light,
				android.R.color.holo_blue_light,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light);
		// Executes after render activity
		swipeRefreshLayout.post(()->{
			swipeRefreshLayout.setRefreshing(true);
			personPresenter.loadData();
		});

		setHasOptionsMenu(true);

		((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

		FragmentManager fragmentManager = getFragmentManager();
		personListFragment = (PersonListFragment)fragmentManager.findFragmentById(R.id.personSwipeRefreshLayout);
		if (personListFragment == null) {
			personListFragment = new PersonListFragment();
			fragmentManager.beginTransaction()
					.add(R.id.personSwipeRefreshLayout, personListFragment)
					.commit();
		}
		personListFragment.setPresenter(personPresenter);
		return view;
	}

	@Override
	public void setPresenter(PersonContract.Presenter presenter) {
		personPresenter = presenter;
	}

	@Override
	public void onRefresh() {

	}
}
