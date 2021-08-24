package com.stockbit.hiring.ui.watchlist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.hiring.R
import com.stockbit.hiring.data.source.remote.response.DataItem

class WatchlistAdapter (private val context: Context)
    : RecyclerView.Adapter<WatchlistAdapter.WatchlistHolder>(){


    private var items :ArrayList<DataItem> =ArrayList<DataItem>()
    lateinit var mInflater: LayoutInflater

    fun setListStocks(newList: ArrayList<DataItem>?) {
        if (newList==null) return
        this.items.addAll(newList)
        notifyItemRangeInserted(itemCount-(newList.size-1),newList.size)
    }

    fun clearListStocks() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchlistHolder {
        mInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemRow = mInflater.inflate(R.layout.item_stock, parent, false)
        return WatchlistHolder(itemRow)
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: WatchlistHolder, position: Int) {
        holder.tv_name.text=items[position].coinInfo?.name
        holder.tv_price.text=items[position].dISPLAY?.iDR?.pRICE
        holder.tv_fullname.text=items[position].coinInfo?.fullName
        holder.tv_lastupdate.text="Last update: "+items[position].dISPLAY?.iDR?.lASTUPDATE
    }

    class WatchlistHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tv_name: TextView = itemView.findViewById<TextView>(R.id.tv_name)
        var tv_price: TextView = itemView.findViewById<TextView>(R.id.tv_price)
        var tv_fullname: TextView = itemView.findViewById<TextView>(R.id.tv_fullname)
        var tv_lastupdate: TextView = itemView.findViewById<TextView>(R.id.tv_lastupdate)

    }
}