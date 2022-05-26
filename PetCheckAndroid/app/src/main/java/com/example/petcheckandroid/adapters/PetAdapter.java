package com.example.petcheckandroid.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.FileProvider;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.data.Pet;

import java.io.File;
import java.util.ArrayList;

public class PetAdapter extends BaseAdapter {

    //base id
    private static final long BASE_ID = 0x1011;

    //ref screen
    private final Context context;

    //ref collection
    private final ArrayList<Pet> pets;

    //construct
    public PetAdapter(Context _context, ArrayList<Pet> _pets){
        context = _context;
        pets = _pets;
    }

    @Override
    public int getCount() {
        if(pets != null){
            return pets.size();
        }

        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(pets != null && position >= 0 && position < pets.size()){
            return pets.get(position);
        }

        return null;
    }

    @Override
    public long getItemId(int position) {
        return BASE_ID + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        String petName = pets.get(position).getName();
        String petType = pets.get(position).getType();
        String uri = "@drawable/" + petType.toLowerCase();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.base_pet_adapter, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }
        else{
            vh = (ViewHolder)convertView.getTag();
        }

        if (petName != null && petType != null){
            // *** SET VIEWHOLDER HERE ***

            vh.ba_image.setImageResource(imageResource);
            vh.ba_tv_main.setText(petName);
            vh.ba_tv_sub.setText(petType);
        }

        return convertView;
    }

    //view holder
    static class ViewHolder{
        final ImageView ba_image;
        final TextView ba_tv_main;
        final TextView ba_tv_sub;

        public ViewHolder(View _layout){
            ba_image = _layout.findViewById(R.id.ba_pets_image);
            ba_tv_main = _layout.findViewById(R.id.ba_pets_tv_main);
            ba_tv_sub = _layout.findViewById(R.id.ba_pets_tv_sub);
        }
    }

}
