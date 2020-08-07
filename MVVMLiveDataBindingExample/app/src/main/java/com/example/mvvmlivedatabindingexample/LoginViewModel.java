package com.example.mvvmlivedatabindingexample;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends BaseObservable {
    private String email;
    private String password;
    private int busy = 8;

    public final ObservableField<String> errorEmail = new ObservableField<>();
    public final ObservableField<String> errorPassword = new ObservableField<>();

    private MutableLiveData<User> userMutableLiveData;

    public LoginViewModel() {
    }

    public LiveData<User> getUser() {
        if(userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    @Bindable
    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public int getBusy() {
        return busy;
    }

    public void setBusy(int busy) {
        this.busy = busy;
        notifyPropertyChanged(BR.busy);
    }

    public void OnLoginClicked(View view) {
        setBusy(0);
        User user = new User(getEmail(), getPassword());
        if (!user.isEmailValid()) {
            errorEmail.set("Enter a valid email address");
        } else {
            errorEmail.set(null);
        }

        if (!user.isPasswordLengthGreaterThan5())
            errorPassword.set("Password Length should be greater than 5");
        else {
            errorPassword.set(null);
        }
        userMutableLiveData.setValue(user);
        setBusy(8);
    }

}
