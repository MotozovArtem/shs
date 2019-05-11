package ru.rienel.shs.mobile.activity.person;

import android.content.Context;

import ru.rienel.shs.mobile.config.AppConfig;
import ru.rienel.shs.mobile.headcontroller.PersonApi;
import ru.rienel.shs.mobile.headcontroller.PersonApiClient;

public class PersonPresenter implements PersonContract.Presenter {
	private PersonContract.View personView;
	private PersonApiClient apiClient;

	public PersonPresenter(PersonContract.View personView, Context context) {
		this.personView = personView;
		apiClient = PersonApi.newClient(AppConfig.HEAD_CONTROLLER_URL, "aaa");
	}

	@Override
	public void start() {

	}

	@Override
	public void loadData() {

	}
}
