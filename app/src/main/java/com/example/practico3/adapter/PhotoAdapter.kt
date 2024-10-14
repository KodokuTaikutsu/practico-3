package com.example.practico3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.practico3.R
import android.widget.ImageView

class PhotoAdapter(private val photos: List<Int>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.item_photo, container, false)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        Glide.with(container.context)
            .load(photos[position])
            .into(imageView)

        container.addView(view)
        return view
    }

    override fun getCount(): Int {
        return photos.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}
