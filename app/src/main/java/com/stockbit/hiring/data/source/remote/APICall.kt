package com.stockbit.hiring.data.source.remote

import com.stockbit.hiring.data.source.remote.response.StockResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface APICall {
    @GET("top/totaltoptiervolfull?limit=10&tsym=IDR")
    fun getStock(
        @Query("page") last_id:Int?
    ): Call<StockResponse>


}