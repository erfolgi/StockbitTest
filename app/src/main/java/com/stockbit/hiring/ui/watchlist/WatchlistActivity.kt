package com.stockbit.hiring.ui.watchlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.stockbit.hiring.R
import com.stockbit.hiring.databinding.ActivityLoginBinding
import com.stockbit.hiring.databinding.ActivityWatchlistBinding
import com.stockbit.hiring.util.PaginationListener
import com.stockbit.hiring.util.ViewModelFactory

class WatchlistActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityWatchlistBinding
    lateinit var adapter: WatchlistAdapter
    private lateinit var vm: WatchlistViewModel

    var pageX=0
    var lastPage=false
    var loading=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityWatchlistBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        //RV setup
        var lmanager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBinding.rvStock.layoutManager=lmanager
        adapter = WatchlistAdapter(this)
        mBinding.rvStock.adapter=adapter

        mBinding.rvStock.addOnScrollListener(object : PaginationListener(lmanager){
            override fun loadMoreItems() {
                if(!lastPage && !loading){
                    loading=true
                    pageX += 1;
                    loadData()
                }
            }

            override val isLastPage: Boolean
                get() = lastPage
            override val isLoading: Boolean
                get() = loading

        })

        vm = obtainViewModel(this)

        mBinding.srStock.setOnRefreshListener {
            pageX=0
            loadData()
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData(){
        if(pageX==0){
            mBinding.srStock.isRefreshing=true
        }

        vm.getStockList(pageX).observe(this, Observer {
            //Log.e("data",it.toString())
            mBinding.srStock.isRefreshing=false
            if(it!=null){
                if(pageX==0){
                    adapter.clearListStocks()
                }
                adapter.setListStocks(it)
                adapter.notifyDataSetChanged()
            }else{

            }
        })
    }

    private fun obtainViewModel(activity: AppCompatActivity): WatchlistViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance()

        return ViewModelProviders.of(activity, factory).get(WatchlistViewModel::class.java)
    }
}