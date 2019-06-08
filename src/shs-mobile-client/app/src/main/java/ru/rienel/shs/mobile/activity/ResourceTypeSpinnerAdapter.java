package ru.rienel.shs.mobile.activity;

import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.domain.ResourceType;

public class ResourceTypeSpinnerAdapter extends ArrayAdapter<ResourceType> {

	private static final String TAG = ResourceTypeSpinnerAdapter.class.getName();

	public ResourceTypeSpinnerAdapter(@NonNull Context context, List<ResourceType> resourceTypeSpinnerItems) {
		super(context, 0, resourceTypeSpinnerItems);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		return createViewForSpinner(position, convertView, parent);
	}

	private View createViewForSpinner(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.resource_type_spinner_row, parent, false);
		}
		ImageView resourceTypeIcon = convertView.findViewById(R.id.resourceTypeSpinnerIcon);
		TextView resourceTypeName = convertView.findViewById(R.id.resourceTypeSpinnerLabel);
		ResourceType item = getItem(position);

		if (item != null) {
			resourceTypeIcon.setImageResource(item.getIconResource64());
			resourceTypeName.setText(item.getLabelResource());
		} else {
			Log.w(TAG, "Item is null!");
		}
		return convertView;
	}

	@Override
	public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		return createViewForSpinner(position, convertView, parent);
	}
}
