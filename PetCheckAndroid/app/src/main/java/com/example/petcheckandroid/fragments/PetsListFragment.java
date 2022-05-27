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

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.activities.AddPetActivity;
import com.example.petcheckandroid.activities.PetDetailsActivity;
import com.example.petcheckandroid.adapters.PetAdapter;
import com.example.petcheckandroid.data.Pet;
import com.example.petcheckandroid.utilities.IntentExtrasUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PetsListFragment extends ListFragment implements View.OnClickListener {

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
        String getRoomDocID();
        void updatePetList();
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

        setPetList();

        FloatingActionButton fab = getActivity().findViewById(R.id.pets_list_fab);
        fab.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.pets_list_fab){
            Log.i(TAG, "onClick: PetList Fab pressed");

            Intent addPetIntent = new Intent(getContext(), AddPetActivity.class);
            addPetIntent.setAction(Intent.ACTION_RUN);
            addPetIntent.putExtra(IntentExtrasUtil.EXTRA_ROOM_DOC_ID, petsListFragmentListener.getRoomDocID());

            addPetActivityLauncher.launch(addPetIntent);
        }    
    }

    private void setPetList(){

        ArrayList<Pet> pets = petsListFragmentListener.getPetsList();
        Log.i(TAG, "setPetList: First pet = " + pets.get(0).getName());
        PetAdapter petAdapter = new PetAdapter(getContext(), pets);

        setListAdapter(petAdapter);
    }

    ActivityResultLauncher<Intent> addPetActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.i(TAG, "onActivityResult: Result Code = " + result.getResultCode());

                    //petsListFragmentListener.updatePetList();

                    if(result.getResultCode() == 21){
                        //setPetList();
                        getActivity().finish();
                    }

                }
            }
    );
}
