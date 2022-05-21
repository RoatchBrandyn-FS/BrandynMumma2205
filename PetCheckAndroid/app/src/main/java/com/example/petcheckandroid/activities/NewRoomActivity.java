package com.example.petcheckandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.fragments.NewRoomFragment;
import com.example.petcheckandroid.utilities.IntentExtrasUtil;
import com.example.petcheckandroid.utilities.RandomNumberGeneratorUtil;

import java.util.ArrayList;

public class NewRoomActivity extends AppCompatActivity implements NewRoomFragment.NewRoomFragmentListener {

    private final String TAG = "NewRoomActivity.TAG";
    private ArrayList<String> roomCodes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent currentIntent = getIntent();
        roomCodes = currentIntent.getStringArrayListExtra(IntentExtrasUtil.EXTRA_ROOM_CODES);
        Log.i(TAG, "onCreate: First room code = " + roomCodes.get(0));

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, NewRoomFragment.newInstance())
                .commit();

    }

    @Override
    public String getRoomCode() {

        String roomCode = RandomNumberGeneratorUtil.randomRoomCodeGenerator();

        while(roomCodes.contains(roomCode)){
            Log.i(TAG, "getRoomCode: " + roomCode);
            roomCode = RandomNumberGeneratorUtil.randomRoomCodeGenerator();
        }

        return roomCode;
    }
}
