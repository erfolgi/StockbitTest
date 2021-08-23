package com.stockbit.hiring.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.stockbit.hiring.data.source.remote.RemoteDataSource
import com.stockbit.hiring.data.source.remote.`interface`.StockInterface
import com.stockbit.hiring.data.source.remote.response.DataItem
import com.stockbit.hiring.data.source.remote.response.StockResponse
import com.stockbit.hiring.util.AppExecutors
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class StockRepository (var remoteData: RemoteDataSource
) :StockDataSource {
    companion object{
        var INSTANCE : StockRepository? =null

        fun getInstance(remoteData: RemoteDataSource): StockRepository? {
            if (INSTANCE == null) {
                synchronized(StockRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            StockRepository(remoteData)
                    }
                }
            }
            return INSTANCE
        }
    }

    //Get Stock
    override fun getStocks(page:Int): LiveData<ArrayList<DataItem>> {
        val movieLive : MutableLiveData<ArrayList<DataItem>> = MutableLiveData<ArrayList<DataItem>>()

        remoteData.getStock(object :StockInterface{
            override fun onLoadSuccess(stockResponse: StockResponse) {
                Log.e("data",stockResponse.toString())
                if(stockResponse.data !=null){

                    movieLive.value= stockResponse.data as ArrayList<DataItem>?
                }
            }
            override fun onLoadFailed() {
                Log.e("data","Failed")
                movieLive.value=null
            }
        },page)
        return movieLive
    }
}