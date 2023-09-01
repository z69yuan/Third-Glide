package com.bumptech.glide.samples.svg

import android.app.Activity
import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.activity_second.btn_load

/**
 *
 *
 * @author  on 2023/8/31
 */
class SecondActivity : FragmentActivity() {
    private lateinit var imageView: ImageView


    val URL_IMAGE = "https://gimg3.baidu.com/search/src=http%3A%2F%2Fpics6.baidu.com%2Ffeed%2F1b4c510fd9f9d72a551fc00e44ba8d38359bbbc3.jpeg%40f_auto%3Ftoken%3D43359227987314b7b0566097ec62e3f5&refer=http%3A%2F%2Fwww.baidu.com&app=2021&size=f360,240&n=0&g=0n&q=75&fmt=auto?sec=1693587600&t=77834a7ea1b20edfa0d7ba7c8e840caf"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        imageView = findViewById<ImageView>(R.id.iv_image)
        btn_load.setOnClickListener {
            Glide.with(this)
                .load(URL_IMAGE)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error)
                .into(imageView)
        }
//        findViewById<Button>(R.id.btn_load).setOnClickListener {
//            GlideApp.with(this)
//                    .`as`(PictureDrawable::class.java)
//                    .placeholder(R.drawable.image_loading)
//                    .error(R.drawable.image_error)
//                    .transition(DrawableTransitionOptions.withCrossFade())
//                    .listener(SvgSoftwareLayerSetter())

//            GlideApp.with(this)
//                    .`as`(PictureDrawable::class.java)
//                    .load(URL_IMAGE)
//                    .placeholder(R.drawable.image_loading)
//                    .error(R.drawable.image_error)
////                    .transition(DrawableTransitionOptions.withCrossFade())
////                    .listener(SvgSoftwareLayerSetter())
//                    .into(imageView)
//        }
    }
}