package ru.rienel.shs.mobile.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.activity.bill.ResourceBillActivity;
import ru.rienel.shs.mobile.activity.indication.IndicationActivity;
import ru.rienel.shs.mobile.activity.meter.MeterActivity;
import ru.rienel.shs.mobile.activity.person.PersonActivity;
import ru.rienel.shs.mobile.activity.settings.AppSettingsActivity;

public class MainRootFragment extends Fragment implements MainContract.View {

	private DrawerLayout drawerLayout;

	private ActionBarDrawerToggle actionBarDrawerToggle;

	private MainContract.Presenter mainPresenter;

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
}
