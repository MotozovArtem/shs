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
import android.widget.ImageView;
import android.widget.TextView;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.domain.ResourceMeter;
import ru.rienel.shs.mobile.domain.ResourceType;
import ru.rienel.shs.mobile.util.Formatters;

public class MeterListFragment extends Fragment {

	private MeterContract.Presenter resourceMeterPresenter;

	private RecyclerView resourceMeterRecycleView;

	private MeterAdapter resourceMeterAdapter;

	private List<ResourceMeter> resourceMeters;

	public static MeterListFragment getInstance(MeterContract.Presenter resourceMeterPresenter) {
		MeterListFragment fragment = new MeterListFragment();
		fragment.setPresenter(resourceMeterPresenter);
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
		presenter.addListener(new ResponseListener());
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

		private TextView serialNumber;

		private TextView added;

		private ImageView resourceType;

		public MeterHolder(@NonNull View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);
			serialNumber = itemView.findViewById(R.id.resourceMeterItemSerialNumber);
			added = itemView.findViewById(R.id.resourceMeterItemAdded);
			resourceType = itemView.findViewById(R.id.resourceMeterItemType);
		}

		public void bind(ResourceMeter resourceMeter) {
			this.resourceMeter = resourceMeter;
			serialNumber.setText(resourceMeter.getSerialNumber());
			added.setText(Formatters.formatDate(resourceMeter.getAddedTime()));
			resourceType.setImageResource(getDrawableResource(resourceMeter.getType()));
		}

		private int getDrawableResource(ResourceType resourceType) {
			switch (resourceType) {
				case ELECTRICITY:
					return R.drawable.flash64;
				case GAS:
					return R.drawable.gas64;
				case HOT_WATER:
					return R.drawable.hot_water64;
				case COLD_WATER:
					return R.drawable.cold_water64;
				default:
					return R.drawable.app_icon;
			}
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
