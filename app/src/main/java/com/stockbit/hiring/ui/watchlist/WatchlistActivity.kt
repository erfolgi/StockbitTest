package com.stockbit.hiring.ui.watchlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.stockbit.hiring.R
import com.stockbit.hiring.databinding.ActivityLoginBinding
import com.stockbit.hiring.databinding.ActivityWatchlistBinding
import com.stockbit.hiring.util.PaginationListener
import com.stockbit.hiring.util.Util
import com.stockbit.hiring.util.ViewModelFactory
import java.net.URISyntaxException

class WatchlistActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityWatchlistBinding
    lateinit var adapter: WatchlistAdapter
    private lateinit var vm: WatchlistViewModel

    var pageX=0
    var lastPage=false
    var loading=false

    lateinit var skeleton: Skeleton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityWatchlistBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        skeleton=mBinding.rvStock.applySkeleton(R.layout.item_stock,10)
        skeleton=Util.skeletonSetup(skeleton)

        //RV setup
        var lmanager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBinding.rvStock.layoutManager=lmanager
        adapter = WatchlistAdapter(this)
        mBinding.rvStock.adapter=adapter
        mBinding.rvStock.setHasFixedSize(true);
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
            lastPage = false
            loadData()
        }


    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData(){
        loading=false
        if(pageX==0){
            skeleton.showSkeleton()
        }

        vm.getStockList(pageX).observe(this, Observer {
            mBinding.srStock.isRefreshing=false
            if(skeleton.isSkeleton()){
                skeleton.showOriginal()
                mBinding.rvStock.adapter=adapter
            }

            if(it!=null){
                mBinding.llEmptyState.visibility=View.GONE
                mBinding.rvStock.visibility=View.VISIBLE
                if(pageX==0){
                    adapter.clearListStocks()
                }
                adapter.setListStocks(it)

            }else{
                if(pageX==0){
                    mBinding.rvStock.visibility=View.GONE
                    mBinding.llEmptyState.visibility=View.VISIBLE
                    mBinding.llEmptyState.setOnClickListener {
                        pageX=0
                        lastPage = false
                        loadData()
                    }
                }
            }
        })
        lastPage = adapter.itemCount>=50
    }

    private fun obtainViewModel(activity: AppCompatActivity): WatchlistViewModel {
        val factory = ViewModelFactory.getInstance()

        return ViewModelProviders.of(activity, factory).get(WatchlistViewModel::class.java)
    }
}