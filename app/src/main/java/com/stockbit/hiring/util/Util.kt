package com.stockbit.hiring.util

import android.content.Context
import android.widget.Toast

object Util {
    fun toast(context: Context, string: String){
        Toast.makeText(context,string, Toast.LENGTH_LONG).show()
    }
}