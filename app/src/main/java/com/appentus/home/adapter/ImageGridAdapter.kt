package com.appentus.home.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.api.load
import coil.request.LoadRequest
import com.appentus.R
import com.appentus.home.model.ImageData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


class ImageGridAdapter(
     var imageList : ArrayList<ImageData>,
     var mContext : Context
     ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view : View? = null

            view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_image_grid,parent,false)
            return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val downloadUrl = imageList[position].downloadUrl
        if (holder is ViewHolder){

            holder.imageView.load(downloadUrl){
                crossfade(true)
                placeholder(R.drawable.images)
            }
        }

    }




    override fun getItemCount(): Int {
        return imageList.size
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.iv_Image)


    }


}