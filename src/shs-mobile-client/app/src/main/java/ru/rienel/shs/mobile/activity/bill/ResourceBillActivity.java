package ru.rienel.shs.mobile.activity.bill;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.db.DatabaseHelper;

public class ResourceBillActivity extends AppCompatActivity {

	private static final String TAG = ResourceBillActivity.class.getName();

	private ResourceBillRootFragment resourceBillRootFragment;

	private ResourceBillPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resource_bill_activity);
		DatabaseHelper dbHelper = new DatabaseHelper(this);

		FragmentManager fragmentManager = getSupportFragmentManager();
		resourceBillRootFragment = (ResourceBillRootFragment)fragmentManager.findFragmentById(R.id.billRootFragmentContainer);
		if (resourceBillRootFragment == null) {
			resourceBillRootFragment = ResourceBillRootFragment.getInstance();
			fragmentManager.beginTransaction()
					.add(R.id.billRootFragmentContainer, resourceBillRootFragment)
					.commit();
		}
		presenter = new ResourceBillPresenter(resourceBillRootFragment,
				PreferenceManager.getDefaultSharedPreferences(this),
				dbHelper);
		resourceBillRootFragment.setPresenter(presenter);
	}

	@Override
	public boolean onSupportNavigateUp() {
		onBackPressed();
		return true;
	}
}
