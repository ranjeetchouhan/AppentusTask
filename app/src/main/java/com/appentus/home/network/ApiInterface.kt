package com.appentus.home.network



import com.appentus.home.model.ImageData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*


interface ApiInterface {



    @GET("list")
    fun getImageData(@Query("page") page : Int) : Single<List<ImageData>>


}