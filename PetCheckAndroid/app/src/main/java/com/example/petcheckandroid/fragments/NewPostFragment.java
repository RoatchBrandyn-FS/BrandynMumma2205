package com.example.petcheckandroid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.data.Pet;
import com.example.petcheckandroid.data.Room;
import com.example.petcheckandroid.data.User;

import java.util.ArrayList;

public class NewPostFragment extends Fragment implements View.OnClickListener {

    private NewPostFragListener newPostFragListener;

    public static NewPostFragment newInstance() {

        Bundle args = new Bundle();

        NewPostFragment fragment = new NewPostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface NewPostFragListener {
        Room getRoom();
        User getUser();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof NewPostFragListener){
            newPostFragListener = (NewPostFragListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button postBtn = getActivity().findViewById(R.id.new_post_btn_post);


        setInitialSpinners();

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.new_post_btn_post){
            //Log.i(TAG, "onClick: ");

            //get user and room
            Room room = newPostFragListener.getRoom();
            User currentUser = newPostFragListener.getUser();



        }

    }

    private void setInitialSpinners(){

        Spinner petSpinner = getActivity().findViewById(R.id.new_post_pet_spinner);

        ArrayList<String> petNames = new ArrayList<>();
        Room room = newPostFragListener.getRoom();

        for (Pet pet: room.getPets()){
            petNames.add(pet.getName());
        }

        ArrayAdapter<String> petSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, petNames);
        petSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        petSpinner.setAdapter(petSpinnerAdapter);
        petSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                setActivitySpinner(room.getPets().get(i));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        setActivitySpinner(room.getPets().get(0));
    }

    private void setActivitySpinner(Pet pet){

        Spinner activitySpinner = getActivity().findViewById(R.id.new_post_activities_spinner);
        ArrayAdapter<CharSequence> activitySpinnerAdapter = null;

        switch (pet.getType()){
            case "Dog":
                activitySpinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.dog_activities, android.R.layout.simple_spinner_item);
            case "Cat":
                activitySpinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.cat_activities, android.R.layout.simple_spinner_item);
            case "Fish":
                activitySpinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.fish_activities, android.R.layout.simple_spinner_item);
        }

        if(activitySpinnerAdapter != null){
            activitySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            activitySpinner.setAdapter(activitySpinnerAdapter);
        }


    }

}
