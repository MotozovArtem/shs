package ru.rienel.shs.mobile.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.activity.bill.ResourceBillActivity;
import ru.rienel.shs.mobile.activity.indication.IndicationActivity;
import ru.rienel.shs.mobile.activity.meter.MeterActivity;
import ru.rienel.shs.mobile.activity.person.PersonActivity;
import ru.rienel.shs.mobile.activity.settings.AppSettingsActivity;

public class MainRootFragment extends Fragment implements MainContract.View, SwipeRefreshLayout.OnRefreshListener {

	private DrawerLayout drawerLayout;

	private ActionBarDrawerToggle actionBarDrawerToggle;

	private MainContract.Presenter mainPresenter;

	private SwipeRefreshLayout swipeRefreshLayout;

	public static MainRootFragment getInstance() {
		MainRootFragment fragment = new MainRootFragment();
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_root_fragment, container, false);
		drawerLayout = view.findViewById(R.id.mainFragmentContainer);
		actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.open, R.string.close);
		drawerLayout.addDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();
		NavigationView navigationView = view.findViewById(R.id.navigation_menu);
		navigationView.setNavigationItemSelectedListener(newNavigationItemSelectedListener());

		swipeRefreshLayout = view.findViewById(R.id.mainSwipeRefreshLayout);
		swipeRefreshLayout.setOnRefreshListener(this);
		swipeRefreshLayout.setColorSchemeResources(
				android.R.color.holo_red_light,
				android.R.color.holo_blue_light,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light
		);

		swipeRefreshLayout.post(() -> {
			swipeRefreshLayout.setRefreshing(true);
			mainPresenter.loadData();
		});

		setHasOptionsMenu(true);
		((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		return view;
	}

	private NavigationView.OnNavigationItemSelectedListener newNavigationItemSelectedListener() {
		return menuItem -> {
			switch (menuItem.getItemId()) {
				case R.id.menu_persons:
					startActivity(prepareChangeActivityIntent(PersonActivity.class));
					break;
				case R.id.menu_bill:
					startActivity(prepareChangeActivityIntent(ResourceBillActivity.class));
					break;
				case R.id.menu_indications:
					startActivity(prepareChangeActivityIntent(IndicationActivity.class));
					break;
				case R.id.menu_meters:
					startActivity(prepareChangeActivityIntent(MeterActivity.class));
					break;
				case R.id.menu_settings:
					startActivity(prepareChangeActivityIntent(AppSettingsActivity.class));
					break;
			}
			return true;
		};
	}

	public Intent prepareChangeActivityIntent(Class<? extends AppCompatActivity> activityClass) {
		Intent intent = new Intent(getContext(), activityClass);
		return intent;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void setPresenter(MainContract.Presenter presenter) {
		this.mainPresenter = presenter;
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
		mainPresenter.loadData();
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		swipeRefreshLayout.setRefreshing(refreshing);
	}
}
