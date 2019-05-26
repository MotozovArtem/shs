package ru.rienel.shs.mobile.activity.meter;

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

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.domain.ResourceMeter;

public class MeterListFragment extends Fragment {

	private MeterContract.Presenter resourceMeterPresenter;

	private RecyclerView resourceMeterRecycleView;

	private MeterAdapter resourceMeterAdapter;

	private List<ResourceMeter> resourceMeters;

	public static MeterListFragment getInstance(MeterContract.Presenter resourceMeterPresenter) {
		MeterListFragment fragment = new MeterListFragment();
		fragment.resourceMeterPresenter = resourceMeterPresenter;
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.meter_list_fragment, container, false);
		resourceMeterRecycleView = view.findViewById(R.id.resourceMeterRecyclerView);
		resourceMeterRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

		updateUi();
		return view;
	}

	public void updateUi() {
		resourceMeterAdapter = new MeterAdapter(resourceMeters);
		resourceMeterRecycleView.setAdapter(resourceMeterAdapter);
	}

	public void updateUi(List<ResourceMeter> resourceMeters) {
		resourceMeterAdapter = new MeterAdapter(resourceMeters);
		resourceMeterRecycleView.setAdapter(resourceMeterAdapter);
	}

	public void setPresenter(MeterContract.Presenter presenter) {
		this.resourceMeterPresenter = presenter;
	}

	class MeterAdapter extends RecyclerView.Adapter<MeterHolder> {

		private List<ResourceMeter> resourceMeters;

		public MeterAdapter(List<ResourceMeter> resourceMeters) {
			this.resourceMeters = resourceMeters;
		}

		@NonNull
		@Override
		public MeterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
			LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
			View view = layoutInflater.inflate(R.layout.meter_list_item, viewGroup, false);
			return new MeterHolder(view);
		}

		@Override
		public void onBindViewHolder(@NonNull MeterHolder meterHolder, int i) {
			ResourceMeter resourceMeter = resourceMeters.get(i);
			meterHolder.bind(resourceMeter);
		}

		@Override
		public int getItemCount() {
			if (resourceMeters == null) {
				return 0;
			}
			return resourceMeters.size();
		}
	}

	private class MeterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		private ResourceMeter resourceMeter;

		public MeterHolder(@NonNull View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);
		}

		public void bind(ResourceMeter resourceMeter) {

		}

		@Override
		public void onClick(View v) {

		}
	}

	class ResponseListener implements MeterPresenter.MeterApiResponseListener {
		@Override
		public void response(MeterPresenter.MeterApiResponseEvent event) {
			List<ResourceMeter> resourceMeters = event.getResourceMeters();

			updateUi(resourceMeters);
		}
	}
}
