package com.bumptech.glide.samples.svg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.List;

/**
 * @author on 2023/9/1
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

  private final List<String> mData;
  private Context mContext;
  public MyAdapter(Context context , List<String> mData) {
    this.mContext = context;
    this.mData = mData;
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ViewGroup rootView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false);
    return new MyViewHolder(rootView);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    String url = mData.get(position);
    GlideApp.with(mContext)
        .load(url)
        .placeholder(R.drawable.image_loading)
        .error(R.drawable.image_error)
        .into(holder.iv);
  }

  @Override
  public int getItemCount() {
    return mData.size();
  }
}
