package ru.rienel.shs.mobile.activity.person;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.rienel.shs.mobile.R;

public class PersonRootFragment extends Fragment implements PersonContract.View {

	private PersonListFragment personListFragment;
	private PersonContract.Presenter personPresenter;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.person_root_fragment, container, false);
		setHasOptionsMenu(true);

		((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


		FragmentManager fragmentManager = getFragmentManager();
		personListFragment = (PersonListFragment)fragmentManager.findFragmentById(R.id.personRecyclerViewContainer);
		if (personListFragment == null) {
			personListFragment = new PersonListFragment();
			fragmentManager.beginTransaction()
					.add(R.id.personRootFragmentContainer, personListFragment)
					.commit();
		}
		personListFragment.setPresenter(personPresenter);
		return view;
	}



	@Override
	public void setPresenter(PersonContract.Presenter presenter) {
		personPresenter = presenter;
	}
}
