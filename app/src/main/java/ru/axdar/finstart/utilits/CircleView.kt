package ru.axdar.finstart.utilits

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr)  {

    private val paint = Paint()
    private val h = this.height
    private val w = this.width
    //private val diameter = if (h > w) h else w
    private val diameter = 48
    private val radius = (diameter / 2).toFloat()
    private val centreX = (diameter / 2).toFloat()
    private val centreY = (diameter / 2).toFloat()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(diameter, diameter)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawCircle(centreX, centreY, radius, paint)
    }

    fun setBgColor(hex: String) {
        paint.color = Color.parseColor(hex)
        invalidate()
    }
}