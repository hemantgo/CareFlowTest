package com.demo.patientapp.datamodel;

import com.google.gson.annotations.SerializedName;

public class AuthRequestDataModel {

    @SerializedName("EmailAddress")
    String EmailAddress;
    @SerializedName("Password")
    String Password;
    @SerializedName("client_id")
    String Client_Id;
    @SerializedName("redirect_url")
    String redirect_url;
    @SerializedName("response_type")
    String response_type;

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getClient_Id() {
        return Client_Id;
    }

    public void setClient_Id(String client_Id) {
        Client_Id = client_Id;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }
}
