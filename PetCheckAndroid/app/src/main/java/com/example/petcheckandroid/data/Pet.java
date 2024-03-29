package com.example.petcheckandroid.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Pet implements Serializable {

    private final String name;
    private final String type;
    private final String description;
    private final String specialInstructions;
    private final String docId;
    private ArrayList<String> activityTypes = new ArrayList<>();
    private ArrayList<String> activityTimes = new ArrayList<>();

    public Pet(String _name, String _type, String _description, String _specialInstructions, String _docId,
               ArrayList<String> _activityTypes, ArrayList<String> _activityTimes){

        name = _name;
        type = _type;
        description = _description;
        specialInstructions = _specialInstructions;
        docId = _docId;
        activityTypes = _activityTypes;
        activityTimes = _activityTimes;

    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public String getDocId() {
        return docId;
    }

    public ArrayList<String> getActivityTypes() {
        return activityTypes;
    }

    public ArrayList<String> getActivityTimes() {
        return activityTimes;
    }

    // *** NEEDS WORK LATER ***
    /* public ArrayList<String> updateActivityTimes(int index, String newTime){
        activityTimes.set(index, newTime);

        return activityTimes;
    }*/
}
