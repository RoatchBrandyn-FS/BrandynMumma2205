package com.example.petcheckandroid.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.fragments.NewRoomFragment;

public class NewRoomActivity extends AppCompatActivity {

    private final String TAG = "NewRoomActivity.TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, NewRoomFragment.newInstance())
                .commit();

    }
}
