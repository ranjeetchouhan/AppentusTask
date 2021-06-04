package com.appentus.home.model


import com.google.gson.annotations.SerializedName

data class ImageData(@SerializedName("author")
                     var author: String = "",
                     @SerializedName("width")
                     var width: Int = 0,
                     @SerializedName("download_url")
                     var downloadUrl: String = "",
                     @SerializedName("id")
                     var id: String = "",
                     @SerializedName("url")
                     var url: String = "",
                     @SerializedName("height")
                     var height: Int = 0)