package com.example.petcheckandroid.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.data.Room;
import com.example.petcheckandroid.fragments.LoginFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "MainActivity.TAG";
    private ArrayList<Room> rooms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchRooms();

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, LoginFragment.newInstance())
                .commit();

    }

    private void fetchRooms(){

        Log.i(TAG, "fetchRooms: Should be loading room data");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("rooms").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    Log.i(TAG, "onComplete: Rooms Count = " + task.getResult().size());
                    for (QueryDocumentSnapshot doc : task.getResult()){
                        Log.i(TAG, "onComplete: doc id = " + doc.getId());
                    }
                }
                else{
                    Log.i(TAG, "onComplete: Failed to get rooms");
                }
            }
        });

                /*addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.i(TAG, "onComplete: Room Count = " + queryDocumentSnapshots.size());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, "onComplete: Rooms didn't load");
            }
        });*/

    }

}
