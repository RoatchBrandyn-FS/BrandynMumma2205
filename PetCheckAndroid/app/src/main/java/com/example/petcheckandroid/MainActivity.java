package com.example.petcheckandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.petcheckandroid.fragments.LoginFragment;
import com.example.petcheckandroid.utilities.Alerts;

public class MainActivity extends AppCompatActivity {

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
