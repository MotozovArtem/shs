package ru.rienel.shs.mobile.activity.meter;

import java.util.Arrays;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.activity.ResourceTypeSpinnerAdapter;
import ru.rienel.shs.mobile.domain.ResourceType;

public class MeterAddFragment extends Fragment implements Spinner.OnItemSelectedListener {

	private static final String TAG = MeterAddFragment.class.getName();

	private Spinner resourceTypeInput;

	private EditText serialNumberInput;

	private Button add;

	private MeterContract.Presenter meterPresenter;

	private ResourceType selectedResourceType;

	public static MeterAddFragment newInstance(MeterContract.Presenter presenter) {
		MeterAddFragment fragment = new MeterAddFragment();
		fragment.setPresenter(presenter);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.meter_add_fragment, container, false);
		resourceTypeInput = view.findViewById(R.id.resourceMeterAddResourceTypeInput);
		ResourceTypeSpinnerAdapter adapter = new ResourceTypeSpinnerAdapter(getContext(),
				Arrays.asList(ResourceType.values()));
		selectedResourceType = adapter.getItem(0);
		resourceTypeInput.setAdapter(adapter);
		resourceTypeInput.setOnItemSelectedListener(this);
		serialNumberInput = view.findViewById(R.id.resourceMeterAddSerialNumberInput);
		add = view.findViewById(R.id.resourceMeterAddButton);
		add.setOnClickListener(this::onAddClick);
		return view;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		selectedResourceType = (ResourceType)parent.getItemAtPosition(position);
		Log.d(TAG, "Selected: " + selectedResourceType);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// AS DEFAULT
		selectedResourceType = ResourceType.ELECTRICITY;
		Log.d(TAG, "Selected: " + selectedResourceType);
	}

	private void onAddClick(View v) {
		String serialNumber = serialNumberInput.getText().toString();
		meterPresenter.addResourceMeter(serialNumber, selectedResourceType);
	}

	public void setPresenter(MeterContract.Presenter meterPresenter) {
		this.meterPresenter = meterPresenter;
		meterPresenter.addListener(new ResponseListener());
	}

	class ResponseListener implements MeterPresenter.MeterApiResponseListener {

		@Override
		public void response(MeterPresenter.MeterApiResponseEvent event) {
			FragmentManager fragmentManager = getFragmentManager();
			if (fragmentManager != null) {
				fragmentManager.popBackStack();
			}
		}
	}
}
