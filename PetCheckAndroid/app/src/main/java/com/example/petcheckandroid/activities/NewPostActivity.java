package com.example.petcheckandroid.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.data.Room;
import com.example.petcheckandroid.data.User;
import com.example.petcheckandroid.fragments.MainPostsListFragment;
import com.example.petcheckandroid.fragments.NewPostFragment;
import com.example.petcheckandroid.utilities.IntentExtrasUtil;

public class NewPostActivity extends AppCompatActivity implements NewPostFragment.NewPostFragListener {

    Room room;
    User currentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent currentIntent = getIntent();
        room = (Room) currentIntent.getSerializableExtra(IntentExtrasUtil.EXTRA_CONFIRMED_ROOM);
        currentUser = (User) currentIntent.getSerializableExtra(IntentExtrasUtil.EXTRA_CURRENT_USER);

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, NewPostFragment.newInstance())
                .commit();
    }

    @Override
    public Room getRoom() {
        return room;
    }

    @Override
    public User getUser() {
        return currentUser;
    }
}
