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
import com.example.petcheckandroid.activities.NewAdminActivity;
import com.example.petcheckandroid.utilities.AlertsUtil;
import com.example.petcheckandroid.utilities.IntentExtrasUtil;
import com.example.petcheckandroid.utilities.RandomNumberGeneratorUtil;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NewRoomFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "NewRoomFragment.TAG";
    public NewRoomFragmentListener newRoomFragmentListener;

    public static NewRoomFragment newInstance() {

        Bundle args = new Bundle();

        NewRoomFragment fragment = new NewRoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface NewRoomFragmentListener {
        String getRoomCode();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof NewRoomFragmentListener){
            newRoomFragmentListener = (NewRoomFragmentListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //set on click listener for continue btn
        Button continueBtn = getActivity().findViewById(R.id.new_room_btn_continue);
        continueBtn.setOnClickListener(this);

        //set random room code
        String roomCodeString = newRoomFragmentListener.getRoomCode();

        TextView roomCodeTV = getActivity().findViewById(R.id.new_room_tv_room_code);
        roomCodeTV.setText(roomCodeString);

    }

    @Override
    public void onClick(View view) {

        //get edit text and textview
        EditText roomNameET = getActivity().findViewById(R.id.new_room_edit_room_name);
        EditText passwordET = getActivity().findViewById(R.id.new_room_edit_password);
        EditText confirmET = getActivity().findViewById(R.id.new_room_edit_confirm);
        TextView roomCodeTV = getActivity().findViewById(R.id.new_room_tv_room_code);

        //get strings from edit text
        String roomNameString = roomNameET.getText().toString().trim();
        String passwordString = passwordET.getText().toString().trim();
        String confirmString = confirmET.getText().toString().trim();
        String roomCodeString = roomCodeTV.getText().toString().trim();

        if (view.getId() == R.id.new_room_btn_continue){
            Log.i(TAG, "onClick: Continue Button Clicked");
            if (roomNameString.isEmpty() || passwordString.isEmpty() || confirmString.isEmpty()){
                AlertsUtil.newRoomError(getContext());
            }
            else{

                if(!passwordString.equals(confirmString)){
                    AlertsUtil.passwordMatchError(getContext());
                }
                else {

                    Intent newAdminIntent = new Intent(getContext(), NewAdminActivity.class);
                    newAdminIntent.setAction(Intent.ACTION_RUN);

                    newAdminIntent.putExtra(IntentExtrasUtil.EXTRA_ROOM_CODE, roomCodeString);
                    newAdminIntent.putExtra(IntentExtrasUtil.EXTRA_ROOM_NAME, roomNameString);
                    newAdminIntent.putExtra(IntentExtrasUtil.EXTRA_ROOM_PASSWORD, passwordString);

                    startActivity(newAdminIntent);
                }


            }
        }

    }


}
