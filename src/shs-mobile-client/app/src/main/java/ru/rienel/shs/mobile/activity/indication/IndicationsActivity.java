package ru.rienel.shs.mobile.activity.indication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.rienel.shs.mobile.R;

public class IndicationsActivity extends AppCompatActivity {

	private IndicationRootFragment indicationRootFragment;
	private IndicationPresenter presenter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.indication_activity);

		FragmentManager fragmentManager = getSupportFragmentManager();
		indicationRootFragment = (IndicationRootFragment)fragmentManager.findFragmentById(R.id.indicationRootFragmentContainer);
		if (indicationRootFragment == null) {
			indicationRootFragment = IndicationRootFragment.getInstance();
			fragmentManager.beginTransaction()
					.add(R.id.indicationRootFragmentContainer, indicationRootFragment)
					.commit();
		}
		presenter = new IndicationPresenter(indicationRootFragment);
		indicationRootFragment.setPresenter(presenter);
	}

	@Override
	public boolean onSupportNavigateUp() {
		onBackPressed();
		return true;
	}
}
