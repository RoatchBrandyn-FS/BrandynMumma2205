package com.example.petcheckandroid.activities;

import android.app.Notification;
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
import com.example.petcheckandroid.utilities.FirebaseUtil;
import com.example.petcheckandroid.utilities.IntentExtrasUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainPostsActivity extends AppCompatActivity implements MainPostsListFragment.MainPostsListFragListener {

    private final String TAG = "MainPostsActivity.TAG";
    private Room room;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set room and user data
        Intent currentIntent = getIntent();
        room = (Room) currentIntent.getSerializableExtra(IntentExtrasUtil.EXTRA_CONFIRMED_ROOM);
        user = (User) currentIntent.getSerializableExtra(IntentExtrasUtil.EXTRA_CURRENT_USER);

        //change Bar title to room name
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(room.getName());
        }

        //get pet data for room
        getPetList();

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, MainPostsListFragment.newInstance())
                .commit();
    }

    @Override
    public void onBackPressed() {

    }

    private void getPetList(){

        //get database and get doc string for room
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

                    Pet newPet = new Pet(_name, _type, _description, _specialInstructions, _activityTypes, _activityTimes);
                    pets.add(newPet);
                }

                room.updatePets(pets);
                Log.i(TAG, "onComplete: FIre Pet in Room = " + room.getPets().get(0).getName());

            }
        });

    }


    @Override
    public Room getRoom() {
        return room;
    }
}
