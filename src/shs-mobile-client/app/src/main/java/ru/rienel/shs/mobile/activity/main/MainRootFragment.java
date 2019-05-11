package ru.rienel.shs.mobile.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.activity.BasePresenter;

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
		setHasOptionsMenu(true);
		((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		return view;
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
