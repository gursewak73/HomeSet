package com.wallpaper.homeset.ui.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class SquareImageView : AppCompatImageView {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(
        context: Context, attrs: AttributeSet,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
    }

    override fun onMeasure(width: Int, height: Int) {
        super.onMeasure(width, height)
        val measuredWidth = measuredWidth
        setMeasuredDimension(measuredWidth, measuredWidth)

    }

}
