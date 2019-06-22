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
import android.widget.TextView;
import android.widget.Toast;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.activity.bill.ResourceBillActivity;
import ru.rienel.shs.mobile.activity.indication.IndicationActivity;
import ru.rienel.shs.mobile.activity.meter.MeterActivity;
import ru.rienel.shs.mobile.activity.person.PersonActivity;
import ru.rienel.shs.mobile.activity.settings.AppSettingsActivity;
import ru.rienel.shs.mobile.domain.Common;
import ru.rienel.shs.mobile.util.Formatters;

public class MainRootFragment extends Fragment implements MainContract.View, SwipeRefreshLayout.OnRefreshListener {

	private DrawerLayout drawerLayout;

	private ActionBarDrawerToggle actionBarDrawerToggle;

	private MainContract.Presenter mainPresenter;

	private SwipeRefreshLayout swipeRefreshLayout;

	private TextView personCount;

	private TextView indicationCount;

	private TextView resourceBillCount;

	private TextView resourceMeterCount;

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

		personCount = view.findViewById(R.id.mainPersonCount);
		indicationCount = view.findViewById(R.id.mainIndicationCount);
		resourceBillCount = view.findViewById(R.id.mainResourceBillCount);
		resourceMeterCount = view.findViewById(R.id.mainResourceMeterCount);
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
		mainPresenter.addListener(new ResponseListener());
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

	public void updateUi(Common common) {
		if (common.getPersonCount() != null) {
			personCount.setText(Formatters.formatLong(common.getPersonCount()));
		}
		if (common.getBillsCount() != null) {
			resourceBillCount.setText(Formatters.formatLong(common.getBillsCount()));
		}
		if (common.getIndicationsCount() != null) {
			indicationCount.setText(Formatters.formatLong(common.getIndicationsCount()));
		}
		if (common.getMetersCount() != null) {
			resourceMeterCount.setText(Formatters.formatLong(common.getMetersCount()));
		}
	}

	class ResponseListener implements MainPresenter.CommonApiResponseListener {

		@Override
		public void response(MainPresenter.CommonApiResponseEvent event) {
			Common common = event.getCommon();

			updateUi(common);
		}
	}
}
