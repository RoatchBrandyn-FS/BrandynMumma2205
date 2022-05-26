package com.example.petcheckandroid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.data.Pet;

import org.w3c.dom.Text;

public class PetDetailsFragment extends Fragment {

    PetDeatilsFragmentListener petDeatilsFragmentListener;

    public static PetDetailsFragment newInstance() {

        Bundle args = new Bundle();

        PetDetailsFragment fragment = new PetDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface PetDeatilsFragmentListener {
        Pet getPetDetails();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof PetDeatilsFragmentListener){
            petDeatilsFragmentListener = (PetDeatilsFragmentListener) context;
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

        setPetDetails(pet.getName(), pet.getType(), pet.getDescription(), imageResource);

    }


    private void setPetDetails(String name, String type, String description, int imageRes){
        ImageView imageView = getActivity().findViewById(R.id.pet_details_image);
        TextView nameTV = getActivity().findViewById(R.id.pet_details_tv_name);
        TextView typeTV = getActivity().findViewById(R.id.pet_details_tv_type);
        TextView descriptionTV = getActivity().findViewById(R.id.pet_details_tv_description);

        imageView.setImageResource(imageRes);
        nameTV.setText(name);
        typeTV.setText(type);
        descriptionTV.setText(description);
    }
}
