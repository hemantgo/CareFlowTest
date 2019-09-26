package com.demo.patientapp.UI.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

public class PatientViewModel implements Parcelable {

    // TODO Remove this : For now dummy Data
    String name;
    String location;
    String NHSNumber;
    String Clinical;

    public PatientViewModel(){}
    protected PatientViewModel(Parcel in) {
        name = in.readString();
        location = in.readString();
        NHSNumber = in.readString();
        Clinical = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(NHSNumber);
        dest.writeString(Clinical);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PatientViewModel> CREATOR = new Creator<PatientViewModel>() {
        @Override
        public PatientViewModel createFromParcel(Parcel in) {
            return new PatientViewModel(in);
        }

        @Override
        public PatientViewModel[] newArray(int size) {
            return new PatientViewModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNHSNumber() {
        return NHSNumber;
    }

    public void setNHSNumber(String NHSNumber) {
        this.NHSNumber = NHSNumber;
    }

    public String getClinical() {
        return Clinical;
    }

    public void setClinical(String clinical) {
        Clinical = clinical;
    }
}
