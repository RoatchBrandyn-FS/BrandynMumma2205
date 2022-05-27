package com.example.petcheckandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.data.Pet;
import com.example.petcheckandroid.data.Room;
import com.example.petcheckandroid.data.User;
import com.example.petcheckandroid.fragments.MainPostsListFragment;
import com.example.petcheckandroid.fragments.PetsListFragment;
import com.example.petcheckandroid.utilities.FirebaseUtil;
import com.example.petcheckandroid.utilities.IntentExtrasUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PetsListActivity extends AppCompatActivity implements PetsListFragment.PetsListFragmentListener {

    private final String TAG = "PetsListActivity.TAG";

    Room room;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set room and user data
        Intent currentIntent = getIntent();
        room = (Room) currentIntent.getSerializableExtra(IntentExtrasUtil.EXTRA_CONFIRMED_ROOM);

        Log.i(TAG, "onCreate: First Pet = " + room.getPets().get(0).getName());

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            // *** Change this later to have room name and current title ***
            actionBar.setTitle(room.getName() + " - My Pets");
        }

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, PetsListFragment.newInstance())
                .commit();


    }

    @Override
    public ArrayList<Pet> getPetsList() {
        return room.getPets();
    }

    @Override
    public String getRoomDocID() {
        return room.getDocId();
    }

    @Override
    public void updatePetList() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String currentRoomDocId = room.getDocId();
        ArrayList<Pet> pets = new ArrayList<>();

        db.collection(FirebaseUtil.COLLECTION_ROOMS + "/" + currentRoomDocId + "/"
                + FirebaseUtil.COLLECTION_PETS).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (QueryDocumentSnapshot doc : task.getResult()){
                    String _name = doc.getString(FirebaseUtil.PETS_FIELD_NAME);
                    //Log.i(TAG, "onComplete: Pet Name: " + _name);
                    String _type = doc.getString(FirebaseUtil.PETS_FIELD_TYPE);
                    String _description = doc.getString(FirebaseUtil.PETS_FIELD_DESCRIPTION);
                    String _specialInstructions = doc.getString(FirebaseUtil.PETS_FIELD_SPECIAL_INSTRUCTIONS);
                    ArrayList<String> _activityTypes =(ArrayList<String>)doc.get(FirebaseUtil.PETS_FIELD_ACTIVITY_TYPES);
                    //Log.i(TAG, "onComplete: First Type List = " + activityTypes.get(0));
                    ArrayList<String> _activityTimes =(ArrayList<String>)doc.get(FirebaseUtil.PETS_FIELD_ACTIVITY_TIMES);

                    Pet newPet = new Pet(_name, _type, _description, _specialInstructions, doc.getId(),  _activityTypes, _activityTimes);
                    pets.add(newPet);
                }

                room.updatePets(pets);
                Log.i(TAG, "onComplete: FIre Pet in Room = " + room.getPets().get(0).getName());

            }
        });
    }


}
