package com.stockbit.hiring.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stockbit.hiring.data.StockRepository
import com.stockbit.hiring.di.Injection
import com.stockbit.hiring.ui.watchlist.WatchlistViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val repo: StockRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(WatchlistViewModel::class.java) -> WatchlistViewModel(repo) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }

    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Injection.provideRepository()?.let {
                            ViewModelFactory(
                                it
                            )
                        }
                    }
                }
            }
            return INSTANCE
        }
    }
}