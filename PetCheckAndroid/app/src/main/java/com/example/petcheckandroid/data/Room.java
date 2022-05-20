package com.example.petcheckandroid.data;

public class Room {

    private final String name;
    private final String roomCode;
    private final String password;

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
}
