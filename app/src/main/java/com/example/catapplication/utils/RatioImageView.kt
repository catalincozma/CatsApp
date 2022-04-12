package com.example.catapplication.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView

class RatioImageView: AppCompatImageView {
    var ratio = 1.0
        set(value) {
            field = value
            requestLayout()
            invalidate()
        }

    constructor(p0: Context) : super(p0)

    constructor(p0: Context, p1: AttributeSet?) : super(p0, p1)

    constructor(p0: Context, p1: AttributeSet?, p2: Int) : super(p0, p1, p2)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        val height = (width * ratio).toInt()
        setMeasuredDimension(width, height)
    }


}