package com.stockbit.hiring.data.source.remote.`interface`

import com.stockbit.hiring.data.source.remote.response.StockResponse

interface StockInterface {
    public open fun onLoadSuccess(stockResponse: StockResponse)
    public open fun onLoadFailed()
}