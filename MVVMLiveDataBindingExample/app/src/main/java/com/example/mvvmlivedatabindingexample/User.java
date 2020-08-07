package com.example.mvvmlivedatabindingexample;

import android.util.Patterns;

import java.util.regex.Pattern;

public class User {

    private String mEmail;
    private String mPassword;

    public User(String mEmail, String mPassword) {
        this.mEmail = mEmail;
        this.mPassword = mPassword;
    }

    public String getEmail() {
        if(mEmail == null) {
            return "";
        }
        return mEmail;
    }

    public String getPassword() {
        if(mPassword == null) {
            return "";
        }
        return mPassword;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    public boolean isPasswordLengthGreaterThan5() {
        return getPassword().length() > 5;
    }
}
