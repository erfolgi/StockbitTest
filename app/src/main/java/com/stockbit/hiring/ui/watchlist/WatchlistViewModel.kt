package com.stockbit.hiring.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stockbit.hiring.data.StockRepository
import com.stockbit.hiring.data.source.remote.response.DataItem

class WatchlistViewModel(var repository: StockRepository): ViewModel() {
    fun getStockList(page:Int): LiveData<ArrayList<DataItem>> {
        return repository.getStocks(page)
    }
}