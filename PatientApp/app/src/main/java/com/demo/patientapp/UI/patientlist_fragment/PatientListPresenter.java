package com.demo.patientapp.UI.patientlist_fragment;

import android.annotation.SuppressLint;

import com.demo.patientapp.UI.base.BasePresenter;
import com.demo.patientapp.UI.viewmodel.PatientListViewModel;
import com.demo.patientapp.UI.viewmodel.PatientRequestViewModel;
import com.demo.patientapp.common.Constants;
import com.demo.patientapp.error.Error;
import com.demo.patientapp.error.ErrorHandler;
import com.demo.patientapp.error.ErrorProvider;
import com.demo.patientapp.mapper.DataModelToViewModelMapper;
import com.demo.patientapp.mapper.ViewModelToDataModelMapper;
import com.demo.patientapp.network.APIClient;
import com.demo.patientapp.network.APIService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PatientListPresenter extends BasePresenter<PatientListView> {

    private APIClient mApiClient;
    private DataModelToViewModelMapper mDataToViewModelMapper;
    private ViewModelToDataModelMapper mViewModelToDataModelMapper;
    private ErrorHandler mErrorHandler;

    public PatientListPresenter(APIClient apiClient, ErrorProvider errorProvider
                                , ViewModelToDataModelMapper viewModelToDataModelMapper
                                , DataModelToViewModelMapper dataToViewModelMapper) {
        mApiClient = apiClient;
        mDataToViewModelMapper = dataToViewModelMapper;
        mViewModelToDataModelMapper = viewModelToDataModelMapper;
        mErrorHandler = new ErrorHandler(errorProvider);
    }

    @SuppressLint("CheckResult")
    public void getPatientsInfo(String token, int teamId, PatientRequestViewModel requestViewModel) {

        mApiClient.create(APIService.class, token).fetchPatientInfo(teamId, mViewModelToDataModelMapper.mapPatientRequestViewModelToDataModel(requestViewModel))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if(view != null) {
                        PatientListViewModel patientListViewModel = mDataToViewModelMapper.mapPatientListDataToViewModel(result);
                        if(patientListViewModel != null) {
                            view.showPatientData(patientListViewModel.getPatientList());
                            if(patientListViewModel.getPatientList().size() == Constants.ITEM_PER_PAGE) {
                                view.addLoadingToAdapter();
                            } else {
                                view.foundLastPage();
                            }
                        } else {
                            view.showError("No Patient data found");
                        }
                    }
                }, throwable -> {
                    if(view != null) {
                        Error error = mErrorHandler.getErrorMessage(throwable);
                        view.showError(error.getMessage());
                    }
                });
    }
}

