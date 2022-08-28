package com.nytimes.data_api.model

import com.google.gson.annotations.SerializedName


data class MostPopularResponseModel(

    @SerializedName("status") var status: String? = null,
    @SerializedName("copyright") var copyright: String? = null,
    @SerializedName("num_results") var numResults: Int? = null,
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf()

)