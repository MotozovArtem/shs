package ru.rienel.shs.mobile.activity.indication.pager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.domain.IndicationRecord;
import ru.rienel.shs.mobile.util.Formatters;

public class IndicationFragment extends Fragment implements IndicationPagerContract.View {

	private IndicationPagerContract.Presenter indicationPagerPresenter;

	private Long indicationId;

	private IndicationRecord record;

	private TextView value;

	private TextView recordDate;

	private TextView resourceMeter;

	private TextView resourceType;

	private ImageView resourceTypeIcon;

	public static IndicationFragment getInstance(Long indicationId) {
		IndicationFragment fragment = new IndicationFragment();
		fragment.indicationId = indicationId;
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.indication_item, container, false);
		value = view.findViewById(R.id.indicationItemValue);
		recordDate = view.findViewById(R.id.indicationItemDate);
		resourceMeter = view.findViewById(R.id.indicationItemMeter);
		resourceType = view.findViewById(R.id.indicationItemType);
		resourceTypeIcon = view.findViewById(R.id.indicationItemTypeIcon);


		record = indicationPagerPresenter.getIndicationRecord(indicationId);

		value.setText(Formatters.formatDouble(record.getValue()));
		recordDate.setText(Formatters.formatDateTime(record.getRecordDate()));
		resourceMeter.setText(record.getDevice().getSerialNumber());
		resourceType.setText(record.getDevice().getType().getLabelResource());
		resourceTypeIcon.setImageResource(record.getDevice().getType().getIconResource128());
		return view;
	}

	@Override
	public void setPresenter(IndicationPagerContract.Presenter indicationPagerPresenter) {
		this.indicationPagerPresenter = indicationPagerPresenter;
	}
}
