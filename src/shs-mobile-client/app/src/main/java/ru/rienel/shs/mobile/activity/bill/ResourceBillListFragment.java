package ru.rienel.shs.mobile.activity.bill;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.TextView;
import android.widget.Toast;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.domain.ResourceBill;
import ru.rienel.shs.mobile.util.Formatters;

public class ResourceBillListFragment extends Fragment {

	private ResourceBillContract.Presenter resourceBillPresenter;

	private RecyclerView resourceBillRecyclerView;

	private List<ResourceBill> resourceBillList = new ArrayList<>();

	private ResourceBillAdapter resourceBillAdapter;

	public static ResourceBillListFragment getInstance(ResourceBillContract.Presenter presenter) {
		ResourceBillListFragment fragment = new ResourceBillListFragment();
		fragment.setPresenter(presenter);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.resource_bill_list_fragment, container, false);
		resourceBillRecyclerView = view.findViewById(R.id.resourceBillRecyclerView);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
				resourceBillRecyclerView.getContext(),
				layoutManager.getOrientation());
		resourceBillRecyclerView.setLayoutManager(layoutManager);
		resourceBillRecyclerView.addItemDecoration(dividerItemDecoration);

		updateUi();
		return view;
	}

	public void setPresenter(ResourceBillContract.Presenter resourceBillPresenter) {
		this.resourceBillPresenter = resourceBillPresenter;
		resourceBillPresenter.addListener(new ResponseListener());
	}

	private void updateUi() {
		resourceBillAdapter = new ResourceBillAdapter(resourceBillList);
		resourceBillRecyclerView.setAdapter(resourceBillAdapter);
	}

	private void updateUi(List<ResourceBill> resourceBillList) {
		resourceBillAdapter = new ResourceBillAdapter(resourceBillList);
		resourceBillRecyclerView.setAdapter(resourceBillAdapter);
	}

	private class ResourceBillAdapter extends RecyclerView.Adapter<ResourceBillHolder> {

		private List<ResourceBill> resourceBills;

		public ResourceBillAdapter(List<ResourceBill> resourceBills) {
			this.resourceBills = resourceBills;
		}

		@NonNull
		@Override
		public ResourceBillHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
			LayoutInflater inflater = LayoutInflater.from(getActivity());
			View view = inflater.inflate(R.layout.resource_bill_list_item, viewGroup, false);
			return new ResourceBillHolder(view);
		}

		@Override
		public void onBindViewHolder(@NonNull ResourceBillHolder resourceBillHolder, int i) {
			ResourceBill resourceBill = resourceBills.get(i);
			resourceBillHolder.bind(resourceBill);
		}

		@Override
		public int getItemCount() {
			if (resourceBills == null) {
				return 0;
			}
			return resourceBills.size();
		}
	}

	private class ResourceBillHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		private ResourceBill resourceBill;

		private TextView serialNumber;

		private TextView costPerUnit;

		private TextView summary;

		public ResourceBillHolder(@NonNull View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);

			serialNumber = itemView.findViewById(R.id.resourceBillListItemSerialNumber);
			costPerUnit = itemView.findViewById(R.id.resourceBillListItemCostPerUnit);
			summary = itemView.findViewById(R.id.resourceBillListItemSummary);
		}

		public void bind(ResourceBill bill) {
			resourceBill = bill;

			serialNumber.setText(resourceBill.getSerialNumber());
			costPerUnit.setText(Formatters.formatDouble(resourceBill.getCostPerUnit()));
			summary.setText(Formatters.formatDouble(resourceBill.getSummary()));
		}

		@Override
		public void onClick(View v) {
			Toast.makeText(getContext(),
					resourceBill.getPerson().getSurname() + " " + resourceBill.getPerson().getName(),
					Toast.LENGTH_SHORT).show();
		}
	}

	class ResponseListener implements ResourceBillPresenter.BillApiResponseListener {

		@Override
		public void response(ResourceBillPresenter.BillApiResponseEvent event) {
			List<ResourceBill> resourceBillList = event.getResourceBills();

			updateUi(resourceBillList);
		}
	}
}
