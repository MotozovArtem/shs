package ru.rienel.shs.mobile.activity.bill;

import java.util.ArrayList;
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
import ru.rienel.shs.mobile.domain.ResourceBill;

public class ResourceBillListFragment extends Fragment {

	private ResourceBillContract.Presenter resourceBillPresenter;

	private RecyclerView recyclerView;

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
		recyclerView = view.findViewById(R.id.resourceBillRecyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		updateUi();
		return view;
	}

	public void setPresenter(ResourceBillContract.Presenter resourceBillPresenter) {
		this.resourceBillPresenter = resourceBillPresenter;
	}

	private void updateUi() {
		resourceBillAdapter = new ResourceBillAdapter(resourceBillList);
		recyclerView.setAdapter(resourceBillAdapter);
	}

	private void updateUi(List<ResourceBill> resourceBillList) {
		resourceBillAdapter = new ResourceBillAdapter(resourceBillList);
		recyclerView.setAdapter(resourceBillAdapter);
	}

	private class ResourceBillAdapter extends RecyclerView.Adapter<BillHolder> {

		private List<ResourceBill> resourceBills;

		public ResourceBillAdapter(List<ResourceBill> resourceBills) {
			this.resourceBills = resourceBills;
		}

		@NonNull
		@Override
		public BillHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
			LayoutInflater inflater = LayoutInflater.from(getActivity());
			View view = inflater.inflate(R.layout.resource_bill_list_item, viewGroup, false);
			return new BillHolder(view);
		}

		@Override
		public void onBindViewHolder(@NonNull BillHolder billHolder, int i) {
			ResourceBill resourceBill = resourceBillList.get(i);
			billHolder.bind(resourceBill);
		}

		@Override
		public int getItemCount() {
			if (resourceBillList == null) {
				return 0;
			}
			return resourceBillList.size();
		}
	}

	private class BillHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		private ResourceBill resourceBill;

		public BillHolder(@NonNull View itemView) {
			super(itemView);
		}

		@Override
		public void onClick(View v) {

		}

		public void bind(ResourceBill resourceBill) {
			this.resourceBill = resourceBill;
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
