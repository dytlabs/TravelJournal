package com.dytstudio.traveljournal.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dytstudio.traveljournal.R;
import com.dytstudio.traveljournal.model.ImageModel;

import java.util.List;

/**
 * Created by Dytstudio.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private List<ImageModel> items;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.iv_photos);
        }
    }

    public ImageAdapter(List<ImageModel> items, Context context){
        this.items = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageModel item = items.get(position);
        Glide.with(context)
                .load(Uri.parse(item.getUrl()))
                .centerCrop()
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
