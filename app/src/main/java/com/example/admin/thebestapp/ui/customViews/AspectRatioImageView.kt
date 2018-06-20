package com.example.admin.thebestapp.ui.customViews

import android.content.Context
import android.util.AttributeSet

class AspectRatioImageView: android.support.v7.widget.AppCompatImageView
{
    constructor(context: Context): super(context)
    
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)
    
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measuredHeight = (measuredWidth.toDouble() / 0.68636363).toInt()
        setMeasuredDimension(measuredWidth, measuredHeight)
    }
}
