package com.example.petcheckandroid.data;

import java.io.Serializable;

public class User implements Serializable {

    private final String firstName;
    private final String lastName;
    private final String username;
    private final String docId;
    private final boolean isAdmin;

    public User(String _firstName, String _lastName, String _username, String _docId, boolean _isAdmin){
        firstName = _firstName;
        lastName = _lastName;
        username = _username;
        docId = _docId;
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

    public String getDocId() {
        return docId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
