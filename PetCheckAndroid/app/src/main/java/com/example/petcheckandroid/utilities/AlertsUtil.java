package com.example.petcheckandroid.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.petcheckandroid.activities.LoginActivity;

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

    public static void noPetsError(Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("No Pets Yet!");
        builder.setMessage("No pets added yet! Go to the Paw button, then add a pet there to start posting activities!");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();

    }

    public static void newPetError(Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Field(s) Empty");
        builder.setMessage("Please fill in all needed information to create a new pet.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();

    }

    public static void isLoggingOut(Context context, Activity activity){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Logout?");
        builder.setMessage("Did you want to logout?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent loginIntent = new Intent(context, LoginActivity.class);
                loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                loginIntent.setAction(Intent.ACTION_MAIN);
                activity.startActivity(loginIntent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();

    }

}
