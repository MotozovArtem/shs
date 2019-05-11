package ru.rienel.shs.mobile.activity.person;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.domain.Person;

public class PersonListFragment extends Fragment implements PersonContract.View {

	private RecyclerView personRecyclerView;
	private PersonAdapter personAdapter;
	private PersonContract.Presenter personPresenter;
	private List<Person> personList = new ArrayList<>();

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.person_list_fragment, container, false);
		personRecyclerView = view.findViewById(R.id.personRecyclerView);
		personRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		updateUi();
		return view;
	}

	public void updateUi() {
		personAdapter = new PersonAdapter(personList);
		personRecyclerView.setAdapter(personAdapter);
	}

	@Override
	public void setPresenter(PersonContract.Presenter presenter) {
		this.personPresenter = presenter;
	}

	private class PersonAdapter extends RecyclerView.Adapter<PersonHolder> {

		public PersonAdapter(List<Person> persons) {
			personList = persons;
		}

		@NonNull
		@Override
		public PersonHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
			LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
			View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
			return new PersonHolder(view);
		}

		@Override
		public void onBindViewHolder(@NonNull PersonHolder personHolder, int i) {
			Person person = personList.get(i);
			personHolder.bind(person);
		}

		@Override
		public int getItemCount() {
			if (personList == null) {
				return 0;
			}
			return personList.size();
		}
	}

	private class PersonHolder extends RecyclerView.ViewHolder {
		private Person person;

		private TextView name;
		private TextView surname;
		private TextView patronymic;

		public PersonHolder(@NonNull View itemView) {
			super(itemView);

			name = itemView.findViewById(R.id.person_list_item_name);
			surname = itemView.findViewById(R.id.person_list_item_surname);
			patronymic = itemView.findViewById(R.id.person_list_item_patronymic);
		}

		public void bind(Person person) {
			this.person = person;
			surname.setText(this.person.getSurname());
			name.setText(this.person.getName());
			patronymic.setText(this.person.getPatronymic());
		}
	}
}
