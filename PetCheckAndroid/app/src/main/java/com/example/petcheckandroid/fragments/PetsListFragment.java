package com.example.petcheckandroid.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.activities.PetDetailsActivity;
import com.example.petcheckandroid.adapters.PetAdapter;
import com.example.petcheckandroid.data.Pet;
import com.example.petcheckandroid.utilities.IntentExtrasUtil;

import java.util.ArrayList;

public class PetsListFragment extends ListFragment {

    private final String TAG = "PetsListFragment.TAG";
    private PetsListFragmentListener petsListFragmentListener;

    public static PetsListFragment newInstance() {

        Bundle args = new Bundle();

        PetsListFragment fragment = new PetsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface PetsListFragmentListener {
        ArrayList<Pet> getPetsList();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof  PetsListFragmentListener){
            petsListFragmentListener = (PetsListFragmentListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_pets, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<Pet> pets = petsListFragmentListener.getPetsList();

        // *** COME BACK LATER TO ADD IMAGES OF PETS ***
        PetAdapter petAdapter = new PetAdapter(getContext(), pets);

        setListAdapter(petAdapter);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ArrayList<Pet> pets = petsListFragmentListener.getPetsList();
        Log.i(TAG, "onListItemClick: Pet Selected = " + pets.get(position).getName());

        Intent petDetailsIntent = new Intent(getContext(), PetDetailsActivity.class);
        petDetailsIntent.setAction(Intent.ACTION_RUN);
        petDetailsIntent.putExtra(IntentExtrasUtil.EXTRA_PET_DETAILS, pets.get(position));

        startActivity(petDetailsIntent);
    }
}
