package com.example.petcheckandroid.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.data.Pet;
import com.example.petcheckandroid.data.Room;
import com.example.petcheckandroid.data.User;
import com.example.petcheckandroid.utilities.DateUtil;
import com.example.petcheckandroid.utilities.FirebaseUtil;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NewPostFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "NewPostFragment.TAG";
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
        postBtn.setOnClickListener(this);

        setInitialSpinners();

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.new_post_btn_post){
            //Log.i(TAG, "onClick: ");
            //get user, room and firebase
            Room room = newPostFragListener.getRoom();
            User currentUser = newPostFragListener.getUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            //get spinners
            Spinner petSpinner = getActivity().findViewById(R.id.new_post_pet_spinner);
            Spinner activitySpinner = getActivity().findViewById(R.id.new_post_activities_spinner);

            ///get chosen pet and activity
            Pet petChosen = room.getPets().get(petSpinner.getSelectedItemPosition());
            String activityChosen = activitySpinner.getSelectedItem().toString();
            Log.i(TAG, "onClick: Chosen Pet = " + petChosen.getName());
            Log.i(TAG, "onClick: Chosen Activity = " + activityChosen);

            //get strings for hash
            String petName = petChosen.getName();
            String petType = petChosen.getType();
            String post = currentUser.getFullName() + " " + activityChosen + " " + petChosen.getName() + " ";
            String timeStamp = DateUtil.setDateTime(new Date());
            String username = currentUser.getUsername();

            //set hash
            Map<String, Object> newPost = new HashMap<>();
            newPost.put(FirebaseUtil.POSTS_FIELD_PET_NAME, petName);
            newPost.put(FirebaseUtil.POSTS_FIELD_PET_TYPE, petType);
            newPost.put(FirebaseUtil.POSTS_FIELD_POST, post);
            newPost.put(FirebaseUtil.POSTS_FIELD_TIME_STAMP, timeStamp);
            newPost.put(FirebaseUtil.POSTS_FIELD_USERNAME, username);

            db.collection(FirebaseUtil.COLLECTION_ROOMS + "/" + room.getDocId()
                    + "/" + FirebaseUtil.COLLECTION_POSTS).add(newPost).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {

                    Toast.makeText(getContext(), "New Post Added ", Toast.LENGTH_SHORT).show();

                    getActivity().finish();

                }
            });

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
