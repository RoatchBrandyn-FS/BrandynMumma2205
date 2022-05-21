package com.example.petcheckandroid.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {

    private final String name;
    private final String roomCode;
    private final String password;
    private ArrayList<User> users = new ArrayList<>();

    public Room(String _name, String _roomCode, String _password){
        name = _name;
        roomCode = _roomCode;
        password = _password;
    }

    public String getName() {
        return name;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void updateUsers(ArrayList<User> updatedUsers){
        users = updatedUsers;
    }
}
