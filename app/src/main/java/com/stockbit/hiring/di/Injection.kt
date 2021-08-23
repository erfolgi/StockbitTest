package com.stockbit.hiring.di

import android.content.Context
import com.stockbit.hiring.data.StockRepository
import com.stockbit.hiring.data.source.remote.APICall
import com.stockbit.hiring.data.source.remote.APIClient
import com.stockbit.hiring.data.source.remote.RemoteDataSource

open class Injection {
    companion object{
        fun provideRepository(): StockRepository? {
            val call= APIClient.getClient(APICall::class.java)
            val remoteRepository = RemoteDataSource.getInstance(call)
            return StockRepository.getInstance(remoteRepository)
        }
    }
}