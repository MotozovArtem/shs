package ru.rienel.shs.mobile.activity.indication;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.activity.indication.pager.IndicationPagerActivity;
import ru.rienel.shs.mobile.domain.IndicationRecord;
import ru.rienel.shs.mobile.domain.ResourceType;
import ru.rienel.shs.mobile.util.Formatters;

public class IndicationListFragment extends Fragment {

	private IndicationContract.Presenter indicationPresenter;

	private IndicationAdapter indicationAdapter;

	private RecyclerView indicationRecordRecyclerView;

	private List<IndicationRecord> indicationRecordList = new ArrayList<>();

	public static IndicationListFragment getInstance(IndicationContract.Presenter indicationPresenter) {
		IndicationListFragment indicationListFragment = new IndicationListFragment();
		indicationListFragment.setPresenter(indicationPresenter);
		return indicationListFragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.indication_list_fragment, container, false);
		indicationRecordRecyclerView = view.findViewById(R.id.indicationRecyclerView);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		DividerItemDecoration itemDecoration = new DividerItemDecoration(indicationRecordRecyclerView.getContext(),
				layoutManager.getOrientation());
		indicationRecordRecyclerView.setLayoutManager(layoutManager);
		indicationRecordRecyclerView.addItemDecoration(itemDecoration);

		updateUi();
		return view;
	}

	private void updateUi() {
		indicationAdapter = new IndicationAdapter(indicationRecordList);
		indicationRecordRecyclerView.setAdapter(indicationAdapter);
	}

	private void updateUi(List<IndicationRecord> indicationRecordList) {
		indicationAdapter = new IndicationAdapter(indicationRecordList);
		indicationRecordRecyclerView.setAdapter(indicationAdapter);
	}

	public void setPresenter(IndicationContract.Presenter indicationPresenter) {
		this.indicationPresenter = indicationPresenter;
		indicationPresenter.addListener(new ResponseListener());
	}

	private class IndicationAdapter extends RecyclerView.Adapter<IndicationHolder> {

		private List<IndicationRecord> indicationRecords;

		public IndicationAdapter(List<IndicationRecord> indicationRecords) {
			this.indicationRecords = indicationRecords;
		}

		@NonNull
		@Override
		public IndicationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
			LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
			View view = layoutInflater.inflate(R.layout.indication_list_item, viewGroup, false);
			return new IndicationHolder(view);
		}

		@Override
		public void onBindViewHolder(@NonNull IndicationHolder indicationHolder, int i) {
			IndicationRecord indicationRecord = indicationRecords.get(i);
			indicationHolder.bind(indicationRecord);
		}

		@Override
		public int getItemCount() {
			if (indicationRecords == null) {
				return 0;
			}
			return indicationRecords.size();
		}
	}

	private class IndicationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		private IndicationRecord indicationRecord;

		private TextView indicationValue;

		private TextView meterLabel;

		private ImageView resourceTypeImage;

		public IndicationHolder(@NonNull View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);

			indicationValue = itemView.findViewById(R.id.indicationListItemValue);
			meterLabel = itemView.findViewById(R.id.indicationListItemMeter);
			resourceTypeImage = itemView.findViewById(R.id.indicationListItemResourceType);
		}

		public void bind(IndicationRecord indicationRecord) {
			this.indicationRecord = indicationRecord;

			indicationValue.setText(Formatters.formatResourceMeterValue(indicationRecord.getValue()));
			meterLabel.setText(indicationRecord.getDevice().getSerialNumber());
			resourceTypeImage.setImageResource(getDrawableResource(indicationRecord.getDevice().getType()));
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
			Intent intent = IndicationPagerActivity.newIntent(getActivity(), indicationRecord.getId());
			startActivity(intent);
		}
	}

	class ResponseListener implements IndicationPresenter.IndicationApiResponseListener {

		@Override
		public void response(IndicationPresenter.IndicationApiResponseEvent event) {
			List<IndicationRecord> indicationRecordList = event.getIndicationRecordList();

			updateUi(indicationRecordList);
		}
	}
}
