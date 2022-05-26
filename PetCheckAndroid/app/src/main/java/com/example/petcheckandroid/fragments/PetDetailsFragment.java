package com.example.petcheckandroid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.data.Pet;
import com.example.petcheckandroid.utilities.DateUtil;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PetDetailsFragment extends Fragment {

    private final String TAG = "PetDetailsFrag.TAG";
    PetDetailsFragmentListener petDeatilsFragmentListener;

    public static PetDetailsFragment newInstance() {

        Bundle args = new Bundle();

        PetDetailsFragment fragment = new PetDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface PetDetailsFragmentListener {
        Pet getPetDetails();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof PetDetailsFragmentListener){
            petDeatilsFragmentListener = (PetDetailsFragmentListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pet_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Pet pet = petDeatilsFragmentListener.getPetDetails();
        String uri = "@drawable/" + pet.getType().toLowerCase();
        int imageResource = getContext().getResources().getIdentifier(uri, null, getContext().getPackageName());

        setPetDetails(pet, imageResource);

    }


    private void setPetDetails(Pet pet, int imageRes){
        ImageView imageView = getActivity().findViewById(R.id.pet_details_image);
        TextView nameTV = getActivity().findViewById(R.id.pet_details_tv_name);
        TextView typeTV = getActivity().findViewById(R.id.pet_details_tv_type);
        TextView descriptionTV = getActivity().findViewById(R.id.pet_details_tv_description);
        TextView specialInstructionsTV = getActivity().findViewById(R.id.pet_details_tv_special_instructions);

        imageView.setImageResource(imageRes);
        nameTV.setText(pet.getName());
        typeTV.setText(pet.getType());
        descriptionTV.setText(pet.getDescription());
        specialInstructionsTV.setText(pet.getSpecialInstructions());

        ArrayList<String> activityType = pet.getActivityTypes();
        ArrayList<String> activityTime = pet.getActivityTimes();

        Date currentTime = new Date();
        Log.i(TAG, "setPetDetails: Current Date: " + currentTime);
        Log.i(TAG, "setPetDetails: Current Date: " + DateUtil.setDateTime(currentTime));

        if(activityType.size() == activityTime.size()){
            Log.i(TAG, "setPetDetails: Lists are equal!");

            for (int i = 0; i < activityTime.size(); i++){

                String actText = activityType.get(i) + ": " + activityTime.get(i);
                setTextView(actText);

            }

        }
        else {
            Log.i(TAG, "setPetDetails: Lists are aren't equal...");
        }


    }

    private void setTextView(String textViewText){

        LinearLayout activityView = getActivity().findViewById(R.id.pet_details_linear_activities);
        TextView newTV = new TextView(getContext());
        newTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTV.setTextSize(18.0f);
        newTV.setText(textViewText);

        activityView.addView(newTV);

    }

}
