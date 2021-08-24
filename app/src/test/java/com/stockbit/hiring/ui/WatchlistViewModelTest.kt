package com.stockbit.hiring.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.stockbit.hiring.data.DataDummy
import com.stockbit.hiring.data.StockRepository
import com.stockbit.hiring.data.source.remote.response.DataItem
import com.stockbit.hiring.ui.watchlist.WatchlistViewModel
import io.mockk.every
import io.mockk.mockkClass
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class WatchlistViewModelTest {
    private var viewModel: WatchlistViewModel? = null

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val stockRepository = mockkClass(StockRepository::class)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = WatchlistViewModel(stockRepository)
    }

    @Test
    fun getMovies() {
        val stocks = DataDummy.dummyStock.data as ArrayList<DataItem>?
        val stockLive : MutableLiveData<ArrayList<DataItem>> = MutableLiveData<ArrayList<DataItem>>()

        stockLive.value = DataDummy.dummyStock.data as ArrayList<DataItem>?
        every {
            (stockRepository.getStocks(0))
        } returns(stockLive)

        val observer = mock<Observer<ArrayList<DataItem>>>()

        viewModel?.getStockList(0)?.observeForever(observer)
        Assert.assertEquals((stockLive.value?.size), viewModel?.getStockList(0)?.value?.size)
        com.nhaarman.mockitokotlin2.verify(observer).onChanged(stocks)
    }
}