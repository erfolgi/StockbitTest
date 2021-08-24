package com.stockbit.hiring.util

import android.content.Context
import android.graphics.Color
import android.widget.Toast
import com.faltenreich.skeletonlayout.Skeleton

object Util {
    fun toast(context: Context, string: String){
        Toast.makeText(context,string, Toast.LENGTH_LONG).show()
    }

    fun skeletonSetup(skeleton: Skeleton): Skeleton {
        skeleton.shimmerDurationInMillis=500
        skeleton.shimmerColor= Color.parseColor("#F0F0F2")
        skeleton.maskColor= Color.parseColor("#C8C8C8")
        skeleton.maskCornerRadius=10.0F
        return skeleton
    }
}