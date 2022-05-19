package com.example.petcheckandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "MainActivity.TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, LoginFragment.newInstance())
                .commit();

    }
}
