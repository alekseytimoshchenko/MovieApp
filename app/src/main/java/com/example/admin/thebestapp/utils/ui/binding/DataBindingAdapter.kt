package com.example.admin.thebestapp.utils.ui.binding

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.example.admin.thebestapp.R
import com.example.admin.thebestapp.utils.ui.GlideApp
import com.squareup.picasso.Picasso

object DataBindingAdapter
{
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageResource(view: ImageView, src: Int?)
    {
        if(src != null)
        {
            GlideApp.with(view).load(src).placeholder(R.mipmap.ic_launcher).into(view)
        }
    }
    
    @BindingAdapter("android:src")
    fun setImageUri(view: ImageView, imageUri: String?)
    {
        if(imageUri == null)
        {
            view.setImageURI(null)
        }
        else
        {
            //I am using picasso hire because Glide does not resize image properly
            Picasso.get()
                    .load(Uri.parse(imageUri))
                    .placeholder(R.mipmap.ic_launcher)
                    .into(view)
        }
    }
    
    @BindingAdapter("android:src")
    fun setImageUri(view: ImageView, imageUri: Uri)
    {
        GlideApp.with(view).load(imageUri)
                .placeholder(R.mipmap.ic_launcher).into(view)
    }
    
    @BindingAdapter("android:src")
    fun setImageDrawable(view: ImageView, drawable: Drawable)
    {
        GlideApp.with(view).load(drawable).placeholder(R.mipmap.ic_launcher).into(view)
    }
}


