package com.appentus.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appentus.R
import com.appentus.home.adapter.ImageGridAdapter
import com.appentus.home.model.ImageData
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity() {


    var homeViewModel : HomeViewModel? = null
    var imageAdapter : ImageGridAdapter? = null
    var imageData = ArrayList<ImageData>()
    lateinit var gridLayoutManager : GridLayoutManager
    var test = "Ranjeet"
    private val VISIBLE_THRESHOLD = 1
    private var lastVisibleItem = 0
    private  var totalItemCount:Int = 0
    var loading = false
    var counter = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        viewModelInitialization()
        recyclerViewInitialization()

        getImageData(0)

        rv_imagegrid.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                totalItemCount = gridLayoutManager.itemCount
                lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition()
                if (!loading
                    && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)
                ) {

                    progressBar.visibility = View.VISIBLE
                    loading = true
                    Log.e("onActivity :",counter.toString())
                    getImageData(counter++)

                }else{
                    progressBar.visibility = View.GONE
                    loading = false
                }
            }
        })

    }

    private fun getImageData(page : Int) {
        homeViewModel?.getImageData(page)?.observe(this, {
            imageData.addAll(it)
            progressBar.visibility = View.GONE
            pb_initial.visibility = View.GONE
            loading = false
            imageAdapter?.notifyDataSetChanged()
        })

    }

    private fun recyclerViewInitialization() {


        gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        rv_imagegrid.layoutManager = gridLayoutManager
        imageAdapter = ImageGridAdapter(imageData, this)
        rv_imagegrid.adapter = imageAdapter

    }

    private fun viewModelInitialization() {
        homeViewModel = ViewModelProviders.of(this, NewInstanceFactory()).get(HomeViewModel()::class.java)

    }
}
