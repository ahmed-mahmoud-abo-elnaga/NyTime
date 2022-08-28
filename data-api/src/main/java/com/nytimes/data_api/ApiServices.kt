package com.nytimes.data_api

import com.nytimes.data_api.model.MostPopularResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("svc/mostpopular/v2/emailed/7.json")
    suspend fun getMostPopular(@Query("api-key") apiKey:String): MostPopularResponseModel


}