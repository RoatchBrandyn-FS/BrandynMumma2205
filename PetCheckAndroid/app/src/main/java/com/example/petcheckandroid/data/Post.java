package com.example.petcheckandroid.data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post implements Serializable {

    private final String post;
    private final String timeStamp;
    private final String username;
    private final String petName;
    private final String petType;

    public Post (String _post, String _timeStamp, String _userName, String _petName, String _petType){
        post = _post;
        timeStamp = _timeStamp;
        username = _userName;
        petName = _petName;
        petType = _petType;
    }

    public String getPost() {
        return post;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getTitle(){
        return username + " & " + petName;
    }

    public String getImageType(){
        return petType;
    }

}
