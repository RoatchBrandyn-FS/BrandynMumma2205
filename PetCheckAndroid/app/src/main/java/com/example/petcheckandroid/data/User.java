package com.example.petcheckandroid.data;

import java.io.Serializable;

public class User implements Serializable {

    private final String firstName;
    private final String lastName;
    private final String username;
    private final boolean isAdmin;

    public User(String _firstName, String _lastName, String _username, boolean _isAdmin){
        firstName = _firstName;
        lastName = _lastName;
        username = _username;
        isAdmin = _isAdmin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
