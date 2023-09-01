package com.bumptech.glide.samples.svg;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * 第三个Acitiviyt
 *
 * @author beancurd on 2023/9/1
 */
public class ThirdActivity extends FragmentActivity {

  //  public static String URL_IMAGE = "https://gimg3.baidu.com/search/src=http%3A%2F%2Fpics6.baidu.com%2Ffeed%2F1b4c510fd9f9d72a551fc00e44ba8d38359bbbc3.jpeg%40f_auto%3Ftoken%3D43359227987314b7b0566097ec62e3f5&refer=http%3A%2F%2Fwww.baidu.com&app=2021&size=f360,240&n=0&g=0n&q=75&fmt=auto?sec=1693587600&t=77834a7ea1b20edfa0d7ba7c8e840caf";
  public static String URL_IMAGE = "https://fetching-androidx.oss-cn-beijing.aliyuncs.com/blog%E7%BC%96%E7%BB%84%205@3x.png";
  public static String URL_IMAGE1 = "https://img2.baidu.com/it/u=47347863,903517934&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1693674000&t=ab959241a2739fc5083e1adaf12cc96e";
  public static String URL_IMAGE2 = "https://gimg3.baidu.com/search/src=http%3A%2F%2Fpics6.baidu.com%2Ffeed%2F1b4c510fd9f9d72a551fc00e44ba8d38359bbbc3.jpeg%40f_auto%3Ftoken%3D43359227987314b7b0566097ec62e3f5&refer=http%3A%2F%2Fwww.baidu.com&app=2021&size=f360,240&n=0&g=0n&q=75&fmt=auto?sec=1693587600&t=77834a7ea1b20edfa0d7ba7c8e840caf";


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    final RecyclerView recyclerView = findViewById(R.id.rcl_v);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    List<String> mData = new ArrayList<>();
    mData.add(URL_IMAGE);
    mData.add(URL_IMAGE1);
    mData.add(URL_IMAGE2);
    recyclerView.setAdapter(new MyAdapter(this,mData));


    final ImageView vx = findViewById(R.id.iv_1);
    GlideApp.with(this)
        .load(URL_IMAGE)
        .placeholder(R.drawable.image_loading)
        .error(R.drawable.image_error)
        .into(vx);

    final ImageView vx1 = findViewById(R.id.iv_2);
    GlideApp.with(this)
        .load(URL_IMAGE2)
        .placeholder(R.drawable.image_loading)
        .error(R.drawable.image_error)
        .into(vx1);

//    final ImageView vx = findViewById(R.id.iv_image);
//    findViewById(R.id.btn_load).setOnClickListener(new OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        Glide.with(ThirdActivity.this)
//            .load(URL_IMAGE)
//            .placeholder(R.drawable.image_loading)
//            .error(R.drawable.image_error)
//            .into(vx);
//      }
//    });

  }
}
