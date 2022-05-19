package com.example.petcheckandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
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

public class NewAdminFragment extends Fragment implements View.OnClickListener {

    public static NewAdminFragment newInstance() {

        Bundle args = new Bundle();

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

        //get strings from edit text
        String firstNameString = firstNameET.getText().toString().trim();
        String lastNameString = lastNameET.getText().toString().trim();
        String usernameString = usernameET.getText().toString().trim();

        if (view.getId() == R.id.new_admin_btn_create){

            if (firstNameString.isEmpty() || lastNameString.isEmpty() || usernameString.isEmpty()){
                AlertsUtil.newUserError(getContext());
            }
            else {
                Intent loginIntent = new Intent(getContext(), LoginActivity.class);
                loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                loginIntent.setAction(Intent.ACTION_MAIN);
                startActivity(loginIntent);
            }

        }

    }
}
