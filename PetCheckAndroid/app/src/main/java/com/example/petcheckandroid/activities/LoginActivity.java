package com.example.petcheckandroid.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.data.Room;
import com.example.petcheckandroid.data.User;
import com.example.petcheckandroid.fragments.LoginFragment;
import com.example.petcheckandroid.utilities.FirebaseUtil;
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

public class LoginActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener {

    private final String TAG = "MainActivity.TAG";
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<String> roomCodes = new ArrayList<>();

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

        db.collection(FirebaseUtil.COLLECTION_ROOMS).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    Log.i(TAG, "onComplete: Rooms Count = " + task.getResult().size());
                    for (QueryDocumentSnapshot doc : task.getResult()){
                        Log.i(TAG, "onComplete: doc id = " + doc.getId());

                        String _roomName = doc.getString(FirebaseUtil.ROOMS_FIELD_ROOM_NAME);
                        String _roomCode = doc.getString(FirebaseUtil.ROOMS_FIELD_ROOM_CODE);
                        String _password = doc.getString(FirebaseUtil.ROOMS_FIELD_PASSWORD);

                        Room newRoom = new Room(_roomName, _roomCode, _password);
                        roomCodes.add(_roomCode);

                        db.collection(FirebaseUtil.COLLECTION_ROOMS + "/" + doc.getId()
                                + "/" + FirebaseUtil.COLLECTION_USERS).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                ArrayList<User> users = new ArrayList<>();

                                for (QueryDocumentSnapshot doc : task.getResult()){

                                    String firstName = doc.getString(FirebaseUtil.USERS_FIELD_FIRST_NAME);
                                    String lastName = doc.getString(FirebaseUtil.USERS_FIELD_LAST_NAME);
                                    String username = doc.getString(FirebaseUtil.USERS_FIELD_USERNAME);
                                    boolean isAdmin = doc.getBoolean(FirebaseUtil.USERS_FIELD_ADMIN);

                                    User newUser = new User(firstName, lastName, username, isAdmin);
                                    users.add(newUser);
                                }

                                newRoom.updateUsers(users);
                                rooms.add(newRoom);
                            }

                        });

                    }
                }
                else{
                    Log.i(TAG, "onComplete: Failed to get rooms");
                }
            }
        });

    }

    @Override
    public ArrayList<String> getRoomCodes() {
        return roomCodes;
    }
}
