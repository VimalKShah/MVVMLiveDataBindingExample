package com.example.mvvmlivedatabindingexample;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<Integer> busy;

    public final MutableLiveData<String> errorEmail = new MutableLiveData<>();
    public final MutableLiveData<String> errorPassword = new MutableLiveData<>();

    private MutableLiveData<User> userMutableLiveData;

    public LoginViewModel() {
    }

    public MutableLiveData<User> getUser() {
        if(userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public LiveData<Integer> getBusy() {
        if(busy == null) {
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }
        return busy;
    }

    public void OnLoginClicked(View view) {
        busy.setValue(0);
        User user = new User(email.getValue(), password.getValue());
        if (!user.isEmailValid()) {
            errorEmail.setValue("Enter a valid email address");
        } else {
            errorEmail.setValue(null);
        }

        if (!user.isPasswordLengthGreaterThan5())
            errorPassword.setValue("Password Length should be greater than 5");
        else {
            errorPassword.setValue(null);
        }
        userMutableLiveData.setValue(user);
        busy.setValue(8);
    }

}
