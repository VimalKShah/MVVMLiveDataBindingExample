package com.example.mvvmlivedatabindingexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mvvmlivedatabindingexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        mainBinding.setLoginViewModel(loginViewModel);
        mainBinding.setLifecycleOwner(this);

        loginViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user.getEmail().length() > 0 && user.getPassword().length() > 0) {
                    Toast.makeText(getApplicationContext(), "Email : " + user.getEmail() + " Password : " + user.getPassword(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
