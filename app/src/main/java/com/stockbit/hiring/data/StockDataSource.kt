package com.stockbit.hiring.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.stockbit.hiring.data.source.remote.response.DataItem
import com.stockbit.repository.utils.Resource

interface StockDataSource {
    fun getStocks(page:Int): LiveData<ArrayList<DataItem>>
}