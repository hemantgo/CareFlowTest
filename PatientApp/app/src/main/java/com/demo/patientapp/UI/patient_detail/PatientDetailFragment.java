package com.demo.patientapp.UI.patient_detail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.demo.patientapp.R;
import com.demo.patientapp.UI.adapter.PaginationAdapter;
import com.demo.patientapp.UI.base.BaseFragment;
import com.demo.patientapp.UI.patientlist_fragment.PatientListPresenter;
import com.demo.patientapp.UI.patientlist_fragment.PatientListView;
import com.demo.patientapp.UI.viewmodel.PatientRequestViewModel;
import com.demo.patientapp.UI.viewmodel.PatientViewModel;
import com.demo.patientapp.common.Constants;
import com.demo.patientapp.common.utils.PaginationListener;
import com.demo.patientapp.common.utils.PrefUtils;
import com.demo.patientapp.error.ErrorProvider;
import com.demo.patientapp.network.APIClient;
import com.demo.patientapp.network.LiveNetworkMonitor;

import java.util.ArrayList;

public class PatientDetailFragment extends BaseFragment implements PatientDetailView{

    private static final String TAG = PatientDetailFragment.class.getSimpleName();
    private OnPatientDetailListener mListener;
    private View mRootView;
    private PatientViewModel mPatientViewModel;

    private TextView mTvClinical;
    private TextView tvLocation;
    private TextView tvNHSNumber;
    private TextView tvName;

    private PatientDetailPresenter mPatientDetailPresenter;

    public static PatientDetailFragment newInstance(PatientViewModel patientViewModel) {
        PatientDetailFragment f = new PatientDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("patientData", patientViewModel);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_patient_detail, container, false);

        mPatientDetailPresenter = new PatientDetailPresenter();
        mPatientDetailPresenter.bind(this);

        mTvClinical = mRootView.findViewById(R.id.tvClinical);
        tvLocation = mRootView.findViewById(R.id.tvLocation);
        tvNHSNumber = mRootView.findViewById(R.id.tvName);
        tvName = mRootView.findViewById(R.id.tvNHSNumber);

        showDataFromArguments(getArguments());

        return mRootView;
    }

    private void showDataFromArguments(Bundle arguments) {
        mPatientViewModel = (PatientViewModel) arguments.get("patientData");
        mPatientDetailPresenter.setPatientDetail(mPatientViewModel);
    }

    public void setListener(OnPatientDetailListener listener) {
        mListener = listener;
    }

    @Override
    public void close() {
        if (mListener != null) {
            mListener.close();
        }
    }

    @Override
    public void setName(String name) {
        tvName.setText(name);
    }

    @Override
    public void setClinicalData(String clinical) {
        mTvClinical.setVisibility(View.VISIBLE);
        mTvClinical.setText(clinical);
    }

    @Override
    public void setLocation(String location) {
        tvLocation.setVisibility(View.VISIBLE);
        tvLocation.setText(location);
    }

    @Override
    public void setNHSNumber(String nhsnumber) {
        tvNHSNumber.setVisibility(View.VISIBLE);
        tvNHSNumber.setText(nhsnumber);
    }

    public interface OnPatientDetailListener {
        public void close();
    }
}
