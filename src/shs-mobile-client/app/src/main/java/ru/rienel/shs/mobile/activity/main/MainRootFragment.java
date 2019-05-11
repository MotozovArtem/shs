package ru.rienel.shs.mobile.activity.main;

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
import android.widget.Toast;

import ru.rienel.shs.mobile.R;

public class MainRootFragment extends Fragment implements MainContract.View {
	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle actionBarDrawerToggle;

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
		return new NavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
				switch (menuItem.getItemId()) {
					case R.id.menu_dashboard:
						Toast.makeText(getContext(), "DASHBOARD", Toast.LENGTH_LONG).show();
						break;
					case R.id.menu_persons:
						Toast.makeText(getContext(), "PERSONS", Toast.LENGTH_LONG).show();
						break;
					case R.id.menu_bill:
						Toast.makeText(getContext(), "BILL", Toast.LENGTH_LONG).show();
						break;
					case R.id.menu_indications:
						Toast.makeText(getContext(), "Indications", Toast.LENGTH_LONG).show();
						break;
					case R.id.menu_meters:
						Toast.makeText(getContext(), "Meters", Toast.LENGTH_LONG).show();
						break;
					case R.id.menu_settings:
						Toast.makeText(getContext(), "Settings", Toast.LENGTH_LONG).show();
						break;
				}
				return true;
			}
		};
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

	}
}
