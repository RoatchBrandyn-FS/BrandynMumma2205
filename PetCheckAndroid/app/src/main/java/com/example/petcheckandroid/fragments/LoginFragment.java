package com.example.petcheckandroid.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.activities.NewRoomActivity;
import com.example.petcheckandroid.utilities.AlertsUtil;
import com.example.petcheckandroid.utilities.IntentExtrasUtil;

import java.util.ArrayList;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "LoginFragment.TAG";
    private static final String ARG_ROOM_CODES = "ARG_ROOM_CODES";
    private LoginFragmentListener loginFragmentListener;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface LoginFragmentListener{
        ArrayList<String> getRoomCodes();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof LoginFragmentListener){
            loginFragmentListener = (LoginFragmentListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //find btn and clickable textView
        Button loginBtn = getActivity().findViewById(R.id.login_btn_login);
        TextView newRoomTV = getActivity().findViewById(R.id.login_textview_new_room);
        loginBtn.setOnClickListener(this);
        newRoomTV.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.login_btn_login){
            Log.i(TAG, "onClick: Login Btn Pressed");

            //set edittext
            EditText roomCodeET = getActivity().findViewById(R.id.login_edit_room_code);
            EditText usernameET = getActivity().findViewById(R.id.login_edit_username);
            EditText passwordET = getActivity().findViewById(R.id.login_edit_password);

            //convert to string
            String roomCodeString = roomCodeET.getText().toString().trim();
            String usernameString = usernameET.getText().toString().trim();
            String passwordString = passwordET.getText().toString().trim();

            if(roomCodeString.isEmpty() || usernameString.isEmpty() || passwordString.isEmpty()){
                AlertsUtil.loginError(getContext());
            }

        }
        else if (view.getId() == R.id.login_textview_new_room) {
            Log.i(TAG, "onClick: New Room TV Pressed");

            ArrayList<String> roomCodes = loginFragmentListener.getRoomCodes();
            Log.i(TAG, "onClick: first code = " + roomCodes.get(0));

            Intent newRoomIntent = new Intent(getContext(), NewRoomActivity.class);
            newRoomIntent.putExtra(IntentExtrasUtil.EXTRA_ROOM_CODES, roomCodes);
            newRoomIntent.setAction(Intent.ACTION_RUN);
            startActivity(newRoomIntent);
        }
    }

}
