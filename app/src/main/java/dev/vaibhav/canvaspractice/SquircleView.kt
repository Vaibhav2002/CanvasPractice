package dev.vaibhav.canvaspractice

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class SquircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private val paths = mutableListOf<CustomPath>()
    private var currentPath: CustomPath
    private var color: Int
    private var width: Float

    init {
        paint.apply {
            isAntiAlias = true
            this.color = Color.RED
            this.style = Paint.Style.STROKE
            this.strokeWidth = 10.0f
            strokeCap = Paint.Cap.ROUND
        }
        color = paint.color
        width = paint.strokeWidth
        currentPath = CustomPath(paint.color, paint.strokeWidth)
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        canvas?.drawRoundRect(rect, 50f, 50f, paint)
        paths.forEach {
            paint.color = it.color
            paint.strokeWidth = it.width
            canvas?.drawPath(it, paint)
        }
        with(currentPath) {
            paint.color = color
            paint.strokeWidth = width
            canvas?.drawPath(this, paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath.color = color
                currentPath.width = width
                currentPath.moveTo(event.x, event.y)
                invalidate()
                true
            }
            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(event.x, event.y)
                invalidate()
                true
            }
            MotionEvent.ACTION_UP -> {
                paths.add(currentPath)
                currentPath = currentPath.copy()
                invalidate()
                true
            }
            else -> false
        }
    }

    fun setBrushColor(color: Int) {
        this.color = color
    }

    fun setBrushWidth(width: Float) {
        this.width = width
    }
}
