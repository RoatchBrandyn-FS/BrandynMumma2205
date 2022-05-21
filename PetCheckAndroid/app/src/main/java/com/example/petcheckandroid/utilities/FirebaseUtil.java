package com.example.petcheckandroid.utilities;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.petcheckandroid.data.Room;
import com.example.petcheckandroid.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FirebaseUtil {

    //collection
    public static final String COLLECTION_ROOMS = "rooms";
    public static final String COLLECTION_USERS = "users";

    //rooms collections field keys
    public static final String ROOMS_FIELD_ROOM_NAME = "name";
    public static final String ROOMS_FIELD_ROOM_CODE = "room_code";
    public static final String ROOMS_FIELD_PASSWORD = "password";

    //users collections field keys
    public static final String USERS_FIELD_FIRST_NAME = "first_name";
    public static final String USERS_FIELD_LAST_NAME = "last_name";
    public static final String USERS_FIELD_ADMIN = "isAdmin";
    public static final String USERS_FIELD_USERNAME = "username";


}