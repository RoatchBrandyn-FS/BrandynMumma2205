package com.example.petcheckandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.data.Pet;
import com.example.petcheckandroid.data.Room;
import com.example.petcheckandroid.data.User;
import com.example.petcheckandroid.fragments.MainPostsListFragment;
import com.example.petcheckandroid.fragments.PetsListFragment;
import com.example.petcheckandroid.utilities.IntentExtrasUtil;

import java.util.ArrayList;

public class PetsListActivity extends AppCompatActivity implements PetsListFragment.PetsListFragmentListener {

    private final String TAG = "PetsListActivity.TAG";

    Room room;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set room and user data
        Intent currentIntent = getIntent();
        room = (Room) currentIntent.getSerializableExtra(IntentExtrasUtil.EXTRA_CONFIRMED_ROOM);

        Log.i(TAG, "onCreate: First Pet = " + room.getPets().get(0).getName());

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            // *** Change this later to have room name and current title ***
            actionBar.setTitle(room.getName() + " - My Pets");
        }

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, PetsListFragment.newInstance())
                .commit();


    }

    @Override
    public ArrayList<Pet> getPetsList() {
        return room.getPets();
    }
}
