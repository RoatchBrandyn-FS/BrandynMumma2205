package com.example.petcheckandroid.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {

    private final String name;
    private final String roomCode;
    private final String password;
    private final String docId;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Pet> pets = new ArrayList<>();

    public Room(String _name, String _roomCode, String _password, String _docId){
        name = _name;
        roomCode = _roomCode;
        password = _password;
        docId = _docId;
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

    public String getDocId() {
        return docId;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void updateUsers(ArrayList<User> updatedUsers){
        users = updatedUsers;
    }

    public void updatePets(ArrayList<Pet> updatedPets) {
        pets = updatedPets;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }
}
