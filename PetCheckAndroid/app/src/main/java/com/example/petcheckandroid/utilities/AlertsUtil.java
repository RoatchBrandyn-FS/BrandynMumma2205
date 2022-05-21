package com.example.petcheckandroid.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertsUtil {

    public static void loginError(Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Field(s) Empty");
        builder.setMessage("Please fill in all needed information to login to your room.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();

    }

    public static void newRoomError(Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Field(s) Empty");
        builder.setMessage("Please fill in all needed information to create a new room.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();

    }

    public static void newUserError(Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Field(s) Empty");
        builder.setMessage("Please fill in all needed information to create a new user.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();

    }

    public static void passwordNewRoomError(Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Password");
        builder.setMessage("Password needs to match with Confirm Password in order to create New Room.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();

    }

    public static void roomCodeMatchError(Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Room Code Not Found");
        builder.setMessage("Room Code provided not found, retype or ask your admin for the Room Code.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();

    }

    public static void userNotInRoomError(Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("User Not Found in Room");
        builder.setMessage("Username not found in room, check for spelling or check with the Room " +
                "Admin to make sure you are added to the room correctly.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();

    }

    public static void passwordLoginError(Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Password Doesn't Match");
        builder.setMessage("Password doesn't match the Room Code, double check the spelling or " +
                "ask the Room Admin for the password.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();

    }

}
