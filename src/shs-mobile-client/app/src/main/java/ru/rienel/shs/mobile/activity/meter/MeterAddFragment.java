package ru.rienel.shs.mobile.activity.meter;

import java.util.Arrays;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
		ResourceTypeSpinnerAdapter adapter = new ResourceTypeSpinnerAdapter(getContext(), Arrays.asList(ResourceType.values()));
		resourceTypeInput.setAdapter(adapter);
		resourceTypeInput.setOnItemSelectedListener(this);
		serialNumberInput = view.findViewById(R.id.resourceMeterAddSerialNumberInput);
		add = view.findViewById(R.id.resourceMeterAddButton);
		return view;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		selectedResourceType = (ResourceType)parent.getItemAtPosition(position);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// AS DEFAULT
		selectedResourceType = ResourceType.ELECTRICITY;
	}

	public void setPresenter(MeterContract.Presenter meterPresenter) {
		this.meterPresenter = meterPresenter;
	}

	class ResponseListener implements MeterPresenter.MeterApiResponseListener {

		@Override
		public void response(MeterPresenter.MeterApiResponseEvent event) {
			// TODO: Catch API response (maybe should be done in parent fragment)
		}
	}
}
