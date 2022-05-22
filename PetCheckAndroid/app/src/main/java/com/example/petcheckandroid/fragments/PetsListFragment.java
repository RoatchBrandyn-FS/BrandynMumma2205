package com.example.petcheckandroid.fragments;

import android.os.Bundle;

import androidx.fragment.app.ListFragment;

public class PetsListFragment extends ListFragment {

    public static PetsListFragment newInstance() {

        Bundle args = new Bundle();

        PetsListFragment fragment = new PetsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
