package com.bumptech.glide.samples.svg;

import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

class MyViewHolder extends ViewHolder {

    public ImageView iv;
    public MyViewHolder(@NonNull ViewGroup parent) {
      super(parent);
      iv = parent.findViewById(R.id.iv_image);
    }
}