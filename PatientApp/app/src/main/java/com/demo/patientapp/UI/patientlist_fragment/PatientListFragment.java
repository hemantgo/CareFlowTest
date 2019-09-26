package com.demo.patientapp.UI.patientlist_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.demo.patientapp.R;
import com.demo.patientapp.UI.activity.PatientDetailActivity;
import com.demo.patientapp.UI.adapter.PaginationAdapter;
import com.demo.patientapp.UI.base.BaseFragment;
import com.demo.patientapp.UI.viewmodel.PatientRequestViewModel;
import com.demo.patientapp.UI.viewmodel.PatientViewModel;
import com.demo.patientapp.common.Constants;
import com.demo.patientapp.common.utils.PaginationListener;
import com.demo.patientapp.common.utils.PrefUtils;
import com.demo.patientapp.error.ErrorProvider;
import com.demo.patientapp.mapper.DataModelToViewModelMapper;
import com.demo.patientapp.mapper.ViewModelToDataModelMapper;
import com.demo.patientapp.network.APIClient;
import com.demo.patientapp.network.LiveNetworkMonitor;

import java.util.ArrayList;

public class PatientListFragment extends BaseFragment implements PatientListView, PaginationAdapter.OnItemClickListener{

    private static final String TAG = PatientListFragment.class.getSimpleName();
    private OnPatientSelectedListener mListener;
    private View mRootView;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressbar;
    private boolean mIsLoading;
    private boolean mIsLastPage = false;
    private String mToken;
    private int mCurrentPage;
    private PaginationAdapter mAdapter;

    private PatientListPresenter mPatientListPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_patient_list, container, false);

        mRecyclerView = mRootView.findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new PaginationAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);

        // set scroll listener to recyclerView so that we can do pagination
        setScrollListener(layoutManager);


        DataModelToViewModelMapper dataModelToViewModelMapper = new DataModelToViewModelMapper();
        ViewModelToDataModelMapper viewModelToDataModelMapper = new ViewModelToDataModelMapper();
        APIClient apiClient = APIClient.getInstance(new LiveNetworkMonitor(getActivity()));
        ErrorProvider errorProvider = new ErrorProvider(getActivity());
        mPatientListPresenter = new PatientListPresenter(apiClient, errorProvider, viewModelToDataModelMapper, dataModelToViewModelMapper);

        loadInitial();
        return mRootView;
    }

    private void loadInitial() {
        showLoading(true);
        mToken = PrefUtils.getToken(getActivity());
        mCurrentPage = 1;
        loadPage();
    }

    /**
     * add scroll listener while user reach in bottom load more will call
     */
    private void setScrollListener(LinearLayoutManager layoutManager) {
        mRecyclerView.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                mIsLoading = true;
                mCurrentPage++;
                loadPage();
            }

            @Override
            public boolean isLastPage() {
                return mIsLastPage;
            }

            @Override
            public boolean isLoading() {
                return mIsLoading;
            }
        });
    }

    @Override
    public void showError(String message) {
        showToast(message);
        mIsLastPage = true;
        mIsLoading = false;
    }

    public void showLoading(boolean show) {
        if(show) {
            mProgressbar.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mProgressbar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showPatientData(ArrayList<PatientViewModel> result) {
        if(mCurrentPage != 1) {
            mAdapter.removeLoadingFooter();
        } else {
            showLoading(false);
        }
        mAdapter.addAll(result);
        mIsLoading = false;
    }

    @Override
    public void addLoadingToAdapter() {
        mAdapter.addLoadingFooter();
    }

    @Override
    public void foundLastPage() {
        mIsLastPage = true;
    }

    @Override
    public void close() {
        if(mListener != null) {
            mListener.close();
        }
    }

    public void setListener(OnPatientSelectedListener listener) {
        mListener = listener;
    }

    private PatientRequestViewModel createRequestModel(int pageNo) {
        PatientRequestViewModel patientRequestViewModel = new PatientRequestViewModel();
        patientRequestViewModel.setPage(pageNo);
        patientRequestViewModel.setItemsPerPage(Constants.ITEM_PER_PAGE);
        ArrayList<String> list = new ArrayList<>();
        list.add("HandoverNotes");
        patientRequestViewModel.setInclude(list);
        return patientRequestViewModel;
    }

    private void loadPage() {
        mPatientListPresenter.getPatientsInfo(mToken, Constants.TEAM_ID, createRequestModel(mCurrentPage));
    }

    @Override
    public void onItemClick(PatientViewModel item) {
        if(mListener != null) {

            // Just In case we want to start an activity from fragment!
//            Intent intent = new Intent(getActivity(), PatientDetailActivity.class);
//            intent.putExtra(Constants.EXTRA_PATIENT_DATA, item);
//            startActivity(intent);


            // Sending this event to activity. As activity can decide what
            // needs to be one on this event. By this this fragment is
            // reusable and can be consumed by other activities and purposes.
            mListener.onPatientSelected(item);
        }
    }

    // This interface can be implemented by the Activity
    public interface OnPatientSelectedListener {
        public void onPatientSelected(PatientViewModel item);
        public void close();
    }
}
