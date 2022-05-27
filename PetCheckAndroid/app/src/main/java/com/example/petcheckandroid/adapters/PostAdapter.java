package com.example.petcheckandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petcheckandroid.R;
import com.example.petcheckandroid.data.Post;

import java.util.ArrayList;

public class PostAdapter extends BaseAdapter {

    //base id
    private static final long BASE_ID = 0x1011;

    //ref screen
    private final Context context;

    //ref collection
    private final ArrayList<Post> posts;

    //construct
    public PostAdapter(Context _context, ArrayList<Post> _posts){
        context = _context;
        posts = _posts;
    }

    @Override
    public int getCount() {
        if(posts != null){
            return posts.size();
        }

        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(posts != null && position >= 0 && position < posts.size()){
            return posts.get(position);
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
        String post = posts.get(position).getPost();
        String title = posts.get(position).getTitle();
        String timeStamp = posts.get(position).getTimeStamp();
        String petType = posts.get(position).getImageType();
        String uri = "@drawable/" + petType.toLowerCase();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.base_post_adapter, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }
        else{
            vh = (ViewHolder) convertView.getTag();
        }

        if (post != null && title != null && timeStamp != null && petType != null){
            // *** SET VIEWHOLDER HERE ***

            vh.post_image.setImageResource(imageResource);
            vh.post_tv_main.setText(post);
            vh.post_tv_title.setText(title);
            vh.post_tv_timestamp.setText(timeStamp);
        }

        return convertView;
    }

    //view holder
    static class ViewHolder{
        final ImageView post_image;
        final TextView post_tv_main;
        final TextView post_tv_title;
        final TextView post_tv_timestamp;

        public ViewHolder(View _layout){
            post_image = _layout.findViewById(R.id.post_image);
            post_tv_main = _layout.findViewById(R.id.post_tv_main);
            post_tv_title = _layout.findViewById(R.id.post_tv_title);
            post_tv_timestamp = _layout.findViewById(R.id.post_tv_time_stamp);
        }
    }
}
