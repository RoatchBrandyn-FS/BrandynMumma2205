package com.example.petcheckandroid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.data.Room;
import com.example.petcheckandroid.utilities.AlertsUtil;
import com.example.petcheckandroid.utilities.FirebaseUtil;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NewUserFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "NewUserFragment.TAG";
    private AddUserFragmentListener addUserFragmentListener;

    public static NewUserFragment newInstance() {

        Bundle args = new Bundle();

        NewUserFragment fragment = new NewUserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface AddUserFragmentListener {
        Room getRoom();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddUserFragmentListener){
            addUserFragmentListener = (AddUserFragmentListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //set on click listener for continue btn
        Button createBtn = getActivity().findViewById(R.id.new_user_btn_create);
        createBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        //get edit text and switch
        EditText firstNameET = getActivity().findViewById(R.id.new_user_edit_first_name);
        EditText lastNameET = getActivity().findViewById(R.id.new_user_edit_last_name);
        EditText usernameET = getActivity().findViewById(R.id.new_user_edit_username);
        SwitchCompat adminSwitch = getActivity().findViewById(R.id.new_user_switch);

        //get strings from edit text and args bundle
        String firstNameString = firstNameET.getText().toString().trim();
        String lastNameString = lastNameET.getText().toString().trim();
        String usernameString = usernameET.getText().toString().trim();

        if (view.getId() == R.id.new_user_btn_create){
            Log.i(TAG, "onClick: isAdmin = " + adminSwitch.isChecked());

            if (firstNameString.isEmpty() || lastNameString.isEmpty() || usernameString.isEmpty()){
                AlertsUtil.newUserError(getContext());
            }
            else{
                addUser(firstNameString,lastNameString, usernameString, adminSwitch.isChecked());
                getActivity().finish();
            }

        }

    }

    private void addUser(String _firstName, String _lastName, String _username, boolean _isAdmin){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Room room = addUserFragmentListener.getRoom();

        Map<String, Object> user = new HashMap<>();
        user.put("first_name", _firstName);
        user.put("isAdmin", _isAdmin);
        user.put("last_name", _lastName);
        user.put("username", _username);

        db.collection(FirebaseUtil.COLLECTION_ROOMS + "/" + room.getDocId()
                + "/" + FirebaseUtil.COLLECTION_USERS).add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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
}
