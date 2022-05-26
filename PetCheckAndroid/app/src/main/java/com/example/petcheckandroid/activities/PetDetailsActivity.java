package com.example.petcheckandroid.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.petcheckandroid.R;
import com.example.petcheckandroid.data.Pet;
import com.example.petcheckandroid.data.Room;
import com.example.petcheckandroid.fragments.PetDetailsFragment;
import com.example.petcheckandroid.fragments.PetsListFragment;
import com.example.petcheckandroid.utilities.IntentExtrasUtil;

public class PetDetailsActivity extends AppCompatActivity implements PetDetailsFragment.PetDetailsFragmentListener {

    private final String TAG = "PetDetailsActivity.TAG";
    Pet pet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set pet data
        Intent currentIntent = getIntent();
        pet = (Pet) currentIntent.getSerializableExtra(IntentExtrasUtil.EXTRA_PET_DETAILS);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            // *** Change this later to have room name and current title ***
            actionBar.setTitle(pet.getName() + " - Details");
        }

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, PetDetailsFragment.newInstance())
                .commit();

    }

    @Override
    public Pet getPetDetails() {
        return pet;
    }
}
