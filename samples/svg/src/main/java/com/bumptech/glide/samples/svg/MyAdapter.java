package com.bumptech.glide.samples.svg;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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
  public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
    String url = mData.get(position);
    GlideApp.with(mContext)
        .load(url)
        .placeholder(R.drawable.image_loading)
        .error(R.drawable.image_error)
        .listener(new RequestListener<Drawable>() {
          @Override
          public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model,
              @NonNull Target<Drawable> target, boolean isFirstResource) {
            return false;
          }

          @Override
          public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model,
              Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
            int width = resource.getIntrinsicWidth();
            int height = resource.getIntrinsicHeight();
            Log.e("MyAdapter", "width = " + width + " height = " + height);
            // 获取屏幕宽度
            int screenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
            // 计算缩放比例
            float scale = (float) screenWidth / width;
            // 计算图片高度
            int imageHeight = (int) (height * scale);
            Log.e("zfc","imageHeight = " + imageHeight);
            (holder.iv.getLayoutParams()).height = imageHeight;
            return false;
          }
        })
        .into(holder.iv);
  }

  @Override
  public int getItemCount() {
    return mData.size();
  }
}
