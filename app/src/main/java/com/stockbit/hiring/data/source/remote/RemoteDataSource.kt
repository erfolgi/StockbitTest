package com.stockbit.hiring.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stockbit.hiring.data.source.ApiResponse
import com.stockbit.hiring.data.source.remote.`interface`.StockInterface
import com.stockbit.hiring.data.source.remote.response.StockResponse
import com.stockbit.hiring.util.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource (){
    private val apiCall= APIClient.getClient(APICall::class.java)

    companion object{
        private var INSTANCE: RemoteDataSource? = null

        fun getInstance(apiCall: APICall): RemoteDataSource {
            if (INSTANCE == null) {
                INSTANCE = RemoteDataSource()
            }
            return INSTANCE as RemoteDataSource
        }
    }

    fun getStock(stockInterface: StockInterface, page:Int){
        EspressoIdlingResource.increment()
        val call = apiCall.getStock(page)
        call.enqueue(object : Callback<StockResponse> {
            override fun onResponse(call: Call<StockResponse>, response: Response<StockResponse>
            ) {
                if(response.body()!=null){
                    EspressoIdlingResource.decrement()
                    response.body()?.let { stockInterface?.onLoadSuccess(it) }
                }
            }
            override fun onFailure(call: Call<StockResponse>, t: Throwable) {
                stockInterface.onLoadFailed()
            }
        })
    }
}