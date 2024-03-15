package com.udacity.asteroidradar.main

import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class DividerItem(
    private val color: Int,
    private val height: Int,
) : RecyclerView.ItemDecoration() {

    private val mPaint = Paint().apply {
        isAntiAlias = true
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        for (child in parent.children) {
            val params = child.layoutParams as RecyclerView.LayoutParams
            val topGap = child.bottom + params.bottomMargin
            val bottomGap = topGap + height
            val leftGap = child.paddingLeft
            val rightGap = child.width - child.paddingRight
            mPaint.color = color
            c.drawRect(
                leftGap.toFloat(),
                topGap.toFloat(),
                rightGap.toFloat(),
                bottomGap.toFloat(),
                mPaint
            )
        }
    }
}