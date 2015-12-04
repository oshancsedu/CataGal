package sifat.Fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import sifat.Adapter.MemoConfirmAdapter;
import sifat.Controller.ServerCommunicator;
import sifat.catagal.R;

import static sifat.Utilities.CommonUtilities.DIALOG_HEADER_AREA_CODE;
import static sifat.Utilities.CommonUtilities.DIALOG_HEADER_AREA_NAME;
import static sifat.Utilities.CommonUtilities.DIALOG_HEADER_DISTRIBUTOR_NAME;
import static sifat.Utilities.CommonUtilities.DIALOG_HEADER_SUPPLY_DATE;

/**
 * Created by sifat on 11/19/2015.
 */
public class ConfirmationMemoFragment extends DialogFragment implements View.OnClickListener {

    private View view;
    private RecyclerView recyclerView;
    private TextView tvDialogHeader;
    private Button btEdit, btConfirm;
    private String header, supplyDate, areaName, areaCode, distributorName;
    private MemoConfirmAdapter adapter;
    private ServerCommunicator serverCommunicator;

    public static ConfirmationMemoFragment newInstance(String supplyDate, String areaName, String areaCode, String distributorName) {
        ConfirmationMemoFragment memoFragment = new ConfirmationMemoFragment();
        Bundle args = new Bundle();
        args.putString(DIALOG_HEADER_SUPPLY_DATE, supplyDate);
        args.putString(DIALOG_HEADER_AREA_NAME, areaName);
        args.putString(DIALOG_HEADER_AREA_CODE, areaCode);
        args.putString(DIALOG_HEADER_DISTRIBUTOR_NAME, distributorName);
        memoFragment.setArguments(args);
        return memoFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supplyDate = getArguments().getString(DIALOG_HEADER_SUPPLY_DATE);
        areaName = getArguments().getString(DIALOG_HEADER_AREA_NAME);
        areaCode = getArguments().getString(DIALOG_HEADER_AREA_CODE);
        distributorName = getArguments().getString(DIALOG_HEADER_DISTRIBUTOR_NAME);
        header = "Area Name: " + areaName + "\nArea Code: " + areaCode + "\nDistributor: " + distributorName + "\nSupply Date: " + supplyDate;
        //showToast(getActivity(), header);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.confirm_memo_fragment, container, false);
        tvDialogHeader = (TextView) view.findViewById(R.id.tvDialogHeader);
        tvDialogHeader.setText(header);
        btEdit = (Button) view.findViewById(R.id.btEdit);
        btEdit.setOnClickListener(this);
        btConfirm = (Button) view.findViewById(R.id.btConfirm);
        btConfirm.setOnClickListener(this);

        setCancelable(false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvSelectedProductList);
        adapter = new MemoConfirmAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.confirm_dialog_frag;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btEdit) {
            dismiss();
        } else if (v.getId() == R.id.btConfirm) {
            dismiss();
            serverCommunicator = new ServerCommunicator(getActivity());
            serverCommunicator.sendMemoInfo(areaName, areaCode, distributorName, supplyDate);
        }
    }
}
