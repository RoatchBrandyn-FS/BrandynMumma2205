package com.example.petcheckandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.petcheckandroid.utilities.Alert;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MainActivity.TAG";

    //elements
    Button loginBtn;
    TextView newRoomTV;
    EditText roomCodeET;
    EditText usernameET;
    EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButtons();
    }

    private void setButtons(){
        //find btn and clickable textView
        loginBtn = findViewById(R.id.login_btn_login);
        newRoomTV = findViewById(R.id.login_textview_new_room);

        loginBtn.setOnClickListener(this);
        newRoomTV.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        if(view.getId() == loginBtn.getId()){
            Log.i(TAG, "onClick: Login Btn Pressed");

            //set edittext
            roomCodeET = findViewById(R.id.login_edit_room_code);
            usernameET = findViewById(R.id.login_edit_username);
            passwordET = findViewById(R.id.login_edit_password);

            //convert to string
            String roomCodeString = roomCodeET.getText().toString().trim();
            String usernameString = usernameET.getText().toString().trim();
            String passwordString = passwordET.getText().toString().trim();

            if(roomCodeString.isEmpty() || usernameString.isEmpty() || passwordString.isEmpty()){
                Alert.loginError(this);
            }

        }
        else if (view.getId() == newRoomTV.getId()) {
            Log.i(TAG, "onClick: New Room TV Pressed");
        }
    }
}
