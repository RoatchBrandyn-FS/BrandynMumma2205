package com.example.petcheckandroid.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.fragments.MainPostsListFragment;
import com.example.petcheckandroid.fragments.NewPostFragment;

public class NewPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, NewPostFragment.newInstance())
                .commit();
    }
}
