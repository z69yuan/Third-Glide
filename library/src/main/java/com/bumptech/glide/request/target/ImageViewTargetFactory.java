package com.bumptech.glide.request.target;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import androidx.annotation.NonNull;

/**
 * A factory responsible for producing the correct type of {@link
 * com.bumptech.glide.request.target.Target} for a given {@link android.view.View} subclass.
 */
public class ImageViewTargetFactory {
  @NonNull
  @SuppressWarnings("unchecked")
  public <Z> ViewTarget<ImageView, Z> buildTarget(
      @NonNull ImageView view, @NonNull Class<Z> clazz) {
    if (Bitmap.class.equals(clazz)) {
      Log.e("ImageViewTargetFactory", "BitmapImageViewTarget");
      return (ViewTarget<ImageView, Z>) new BitmapImageViewTarget(view);
    } else if (Drawable.class.isAssignableFrom(clazz)) {
      Log.e("ImageViewTargetFactory", "DrawableImageViewTarget");
      DrawableImageViewTarget drawableImageViewTarget = new DrawableImageViewTarget(view);
      drawableImageViewTarget.waitForLayout();
      return (ViewTarget<ImageView, Z>) drawableImageViewTarget;
    } else {
      throw new IllegalArgumentException(
          "Unhandled class: " + clazz + ", try .as*(Class).transcode(ResourceTranscoder)");
    }
  }
}
