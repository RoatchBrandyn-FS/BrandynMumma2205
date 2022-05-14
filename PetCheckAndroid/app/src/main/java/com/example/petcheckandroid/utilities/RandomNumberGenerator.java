package com.example.petcheckandroid.utilities;

import android.content.Intent;
import android.util.Log;

import java.util.Random;

public class RandomNumberGenerator {

    public static String randomRoomCodeGenerator(){

        final String TAG = "RandomNumGen.TAG";

        Random randomNum = new Random();
        StringBuilder roomCode = new StringBuilder();

        for (int i = 0; i < 16; i++){
            roomCode.append(randomNum.nextInt(9));
        }

        roomCode.insert(12, "-");
        roomCode.insert(8, "-");
        roomCode.insert(4, "-");

        String finalRoomCode = roomCode.toString();

        Log.i(TAG, "randomRoomCodeGenerator: room code = " + finalRoomCode);

        return finalRoomCode;
    }



}
