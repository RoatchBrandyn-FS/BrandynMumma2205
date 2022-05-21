package com.example.petcheckandroid.fragments;

import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.petcheckandroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainPostsListFragment extends ListFragment implements View.OnClickListener {

    private final String TAG = "MainPostsListFrag.TAG";

    public static MainPostsListFragment newInstance() {

        Bundle args = new Bundle();

        MainPostsListFragment fragment = new MainPostsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        //setup list here with args bundle
        
        //set floating buttons, menu will be in separate area
        FloatingActionButton fab = getActivity().findViewById(R.id.main_post_fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_posts, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        
        if (item.getTitle().equals("settings")){
            Log.i(TAG, "onOptionsItemSelected: settings pressed");
        }
        else if (item.getTitle().equals("add pets")){
            Log.i(TAG, "onOptionsItemSelected: add pets pressed");
        }
        
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.main_post_fab){
            Log.i(TAG, "onClick: fab pressed");
        }
    }
}
