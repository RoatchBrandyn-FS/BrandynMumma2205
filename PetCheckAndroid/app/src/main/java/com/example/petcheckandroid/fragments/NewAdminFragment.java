package com.example.petcheckandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.activities.LoginActivity;
import com.example.petcheckandroid.utilities.AlertsUtil;
import com.example.petcheckandroid.utilities.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NewAdminFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "NewAdminFrag.TAG";
    private static final String ARG_ROOM_NAME = "KEY_ROOM_NAME";
    private static final String ARG_ROOM_CODE = "KEY_ROOM_CODE";
    private static final String ARG_PASSWORD = "KEY_PASSWORD";

    public static NewAdminFragment newInstance(String _roomName, String _roomCode, String _password) {

        Bundle args = new Bundle();
        args.putString(ARG_ROOM_NAME, _roomName);
        args.putString(ARG_ROOM_CODE, _roomCode);
        args.putString(ARG_PASSWORD, _password);

        NewAdminFragment fragment = new NewAdminFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //set on click listener for continue btn
        Button createBtn = getActivity().findViewById(R.id.new_admin_btn_create);
        createBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        //get edit text
        EditText firstNameET = getActivity().findViewById(R.id.new_admin_edit_first_name);
        EditText lastNameET = getActivity().findViewById(R.id.new_admin_edit_last_name);
        EditText usernameET = getActivity().findViewById(R.id.new_admin_edit_username);

        //get strings from edit text and args bundle
        String firstNameString = firstNameET.getText().toString().trim();
        String lastNameString = lastNameET.getText().toString().trim();
        String usernameString = usernameET.getText().toString().trim();
        String roomName = getArguments().getString(ARG_ROOM_NAME);
        String roomCode = getArguments().getString(ARG_ROOM_CODE);
        String password = getArguments().getString(ARG_PASSWORD);

        if (view.getId() == R.id.new_admin_btn_create){

            if (firstNameString.isEmpty() || lastNameString.isEmpty() || usernameString.isEmpty()){
                AlertsUtil.newUserError(getContext());
            }
            else {

                addNewRoom(roomName, roomCode, password,
                        firstNameString, lastNameString, usernameString);

                Intent loginIntent = new Intent(getContext(), LoginActivity.class);
                loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                loginIntent.setAction(Intent.ACTION_MAIN);
                startActivity(loginIntent);
            }

        }

    }

    private void addNewRoom(String _name, String _roomCode, String _password, String _firstName, String _lastName,
                            String _username){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> room = new HashMap<>();
        room.put("name", _name);
        room.put("room_code", _roomCode);
        room.put("password", _password);

        Map<String, Object> user = new HashMap<>();
        user.put("first_name", _firstName);
        user.put("isAdmin", true);
        user.put("last_name", _lastName);
        user.put("username", _username);

        db.collection(FirebaseUtil.COLLECTION_ROOMS).add(room).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.i(TAG, "onSuccess: Doc added with id = " + documentReference.getId());

                db.collection(FirebaseUtil.COLLECTION_ROOMS + "/" + documentReference.getId()
                                + "/" + FirebaseUtil.COLLECTION_USERS).add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.i(TAG, "onSuccess: Should have added users");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i(TAG, "onFailure: Should Not Add to Firebase \n" + e);
                            }
                        });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, "onFailure: " + e);
            }
        });

    }

}
