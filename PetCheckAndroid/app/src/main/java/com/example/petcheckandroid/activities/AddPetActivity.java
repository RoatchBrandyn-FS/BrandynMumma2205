package com.example.petcheckandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.fragments.AddPetFragment;
import com.example.petcheckandroid.utilities.IntentExtrasUtil;

public class AddPetActivity extends AppCompatActivity implements AddPetFragment.AddPetFragmentListener {

    private final String TAG = "AddPetActivity.TAG";
    String roomDocId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent currentIntent = getIntent();
        roomDocId = currentIntent.getStringExtra(IntentExtrasUtil.EXTRA_ROOM_DOC_ID);
        Log.i(TAG, "onCreate: DocId = " + roomDocId);

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, AddPetFragment.newInstance())
                .commit();

    }

    @Override
    public String getDocID() {
        return roomDocId;
    }
}
