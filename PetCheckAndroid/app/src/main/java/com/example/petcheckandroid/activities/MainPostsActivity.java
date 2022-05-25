package com.example.petcheckandroid.activities;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.data.Room;
import com.example.petcheckandroid.data.User;
import com.example.petcheckandroid.fragments.MainPostsListFragment;
import com.example.petcheckandroid.utilities.IntentExtrasUtil;

public class MainPostsActivity extends AppCompatActivity {

    private final String TAG = "MainPostsActivity.TAG";
    private Room room;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent currentIntent = getIntent();
        room = (Room) currentIntent.getSerializableExtra(IntentExtrasUtil.EXTRA_CONFIRMED_ROOM);
        user = (User) currentIntent.getSerializableExtra(IntentExtrasUtil.EXTRA_CURRENT_USER);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(room.getName());
        }

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, MainPostsListFragment.newInstance())
                .commit();
    }

    @Override
    public void onBackPressed() {

    }
}
