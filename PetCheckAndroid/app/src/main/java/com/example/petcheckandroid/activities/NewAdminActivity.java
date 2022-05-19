package com.example.petcheckandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.fragments.NewAdminFragment;
import com.example.petcheckandroid.utilities.IntentExtrasUtil;

public class NewAdminActivity extends AppCompatActivity {

    private final String TAG = "NewAdminFragment.TAG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent currentIntent = getIntent();

        String roomCode = currentIntent.getStringExtra(IntentExtrasUtil.EXTRA_ROOM_CODE);
        String roomName = currentIntent.getStringExtra(IntentExtrasUtil.EXTRA_ROOM_NAME);
        String roomPassword = currentIntent.getStringExtra(IntentExtrasUtil.EXTRA_ROOM_PASSWORD);

        Log.i(TAG, "onCreate: Room Code = " + roomCode);
        Log.i(TAG, "onCreate: Room Name = " + roomName);
        Log.i(TAG, "onCreate: Room Password = " + roomPassword);

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, NewAdminFragment.newInstance())
                .commit();

    }
}
