package com.example.petcheckandroid.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.data.Room;
import com.example.petcheckandroid.fragments.MainPostsListFragment;
import com.example.petcheckandroid.fragments.NewUserFragment;
import com.example.petcheckandroid.utilities.IntentExtrasUtil;

public class NewUserActivity extends AppCompatActivity implements NewUserFragment.AddUserFragmentListener {

    Room room;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set room and user data
        Intent currentIntent = getIntent();
        room = (Room) currentIntent.getSerializableExtra(IntentExtrasUtil.EXTRA_CONFIRMED_ROOM);

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, NewUserFragment.newInstance())
                .commit();

    }

    @Override
    public Room getRoom() {
        return room;
    }
}
