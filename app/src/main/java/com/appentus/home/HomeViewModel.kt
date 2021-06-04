package com.appentus.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.appentus.home.model.ImageData
import com.appentus.home.network.ApiClient
import com.appentus.home.network.ApiInterface
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

import retrofit2.Retrofit

class HomeViewModel() : ViewModel() {

    private var retrofit : Retrofit? = null
    private var apiInterface : ApiInterface? = null



    init {
        retrofit = ApiClient.getClient()
        if (retrofit!=null){
            apiInterface = retrofit!!.create(ApiInterface::class.java)
        }
    }


    fun getImageData(page: Int): MutableLiveData<List<ImageData>> {


        val imageResponse  = MutableLiveData<List<ImageData>>()
        apiInterface?.getImageData(page)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<List<ImageData>>(){
                override fun onSuccess(t: List<ImageData>?) {
                    imageResponse.value = t
                    Log.e("onSuccess Page",page.toString())
                    Log.e("onSuccess",t?.size.toString())
                }

                override fun onError(e: Throwable?) {
                    Log.e("onError",e?.localizedMessage.toString())
                }

            })

        return imageResponse
    }

}