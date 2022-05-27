package com.example.petcheckandroid.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.activities.AddPetActivity;
import com.example.petcheckandroid.utilities.AlertsUtil;
import com.example.petcheckandroid.utilities.FirebaseUtil;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddPetFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "AddPetFragment.TAG";
    private AddPetFragmentListener addPetFragmentListener;

    public static AddPetFragment newInstance() {

        Bundle args = new Bundle();

        AddPetFragment fragment = new AddPetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface AddPetFragmentListener {
        String getDocID();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof AddPetFragmentListener){
            addPetFragmentListener = (AddPetFragmentListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_pet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button createBtn = getActivity().findViewById(R.id.add_pet_btn_create);
        createBtn.setOnClickListener(this);
        setSpinner();

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.add_pet_btn_create){

            FirebaseFirestore db = FirebaseFirestore.getInstance();


            //get views needed for access
            EditText nameET = getActivity().findViewById(R.id.add_pet_et_name);
            EditText descriptionET = getActivity().findViewById(R.id.add_pet_et_description);
            EditText specialInstructionET = getActivity().findViewById(R.id.add_pet_et_special_instructions);
            Spinner typeSpinner = getActivity().findViewById(R.id.add_pet_et_spinner);

            //get strings from views
            String nameString = nameET.getText().toString().trim();
            String descriptionString = descriptionET.getText().toString().trim();
            String specialInstructionsString = specialInstructionET.getText().toString().trim();
            String typeString = typeSpinner.getSelectedItem().toString();


            if (nameString.isEmpty() || descriptionString.isEmpty() || specialInstructionsString.isEmpty()){
                AlertsUtil.newPetError(getContext());
            }
            else {

                //get String[]'s
                String[] activityTypes = {};
                String[] activityTimes = {};

                switch (typeString){
                    case "Cat":
                        activityTypes = getContext().getResources().getStringArray(R.array.cat_activities);
                        activityTimes = new String[] {"N/A", "N/A"};
                    case "Dog":
                        activityTypes = getContext().getResources().getStringArray(R.array.dog_activities);
                        activityTimes = new String[] {"N/A", "N/A", "N/A"};
                    case "Fish":
                        activityTypes = getContext().getResources().getStringArray(R.array.fish_activities);
                        activityTimes = new String[] {"N/A", "N/A"};
                }

                Log.i(TAG, "onClick: Act Type/Time = " + activityTypes[0] + "/" + activityTimes[0]);

                //set hash map for add
                Map<String, Object> newPet = new HashMap<>();
                newPet.put("name", nameString);
                newPet.put("type", typeString);
                newPet.put("description", descriptionString);
                newPet.put("special_instructions", specialInstructionsString);
                newPet.put("activity_types", Arrays.asList(activityTypes));
                newPet.put("activity_times", Arrays.asList(activityTimes));


                db.collection(FirebaseUtil.COLLECTION_ROOMS + "/" + addPetFragmentListener.getDocID()
                        + "/" + FirebaseUtil.COLLECTION_PETS).add(newPet).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Intent resultIntent = new Intent();
                        getActivity().setResult(21, resultIntent);

                        Toast.makeText(getContext(), "Added " + nameString, Toast.LENGTH_SHORT).show();

                        getActivity().finish();

                    }
                });



            }

        }
    }

    private void setSpinner(){
        Spinner petTypeSpinner = getActivity().findViewById(R.id.add_pet_et_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.pet_types, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        petTypeSpinner.setAdapter(spinnerAdapter);
        petTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] petTypes = getContext().getResources().getStringArray(R.array.pet_types);

                Log.i(TAG, "onItemSelected: Pet Type = " + petTypes[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
