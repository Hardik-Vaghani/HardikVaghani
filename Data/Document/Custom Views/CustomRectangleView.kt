package com.hardik.customveiw.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

class CustomRectangleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // Rectangle paint
    private val rectanglePaint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    // Text paint
    private val textPaint = TextPaint().apply {
        color = Color.DKGRAY
        textSize = 50f
        isAntiAlias = true
    }

    // Text properties
    private var text: String = "Hardik with always KRISHNA!!!"
    private var textAlignment: Layout.Alignment = Layout.Alignment.ALIGN_CENTER

    // Optional: Customize rectangle color
    fun setRectangleColor(color: Int) {
        rectanglePaint.color = color
        invalidate()
    }

    // Method to set text
    fun setText(newText: String) {
        text = newText
        invalidate()
    }

    // Method to set text alignment
    fun setTextAlignment(alignment: Layout.Alignment) {
        textAlignment = alignment
        invalidate()
    }

    // Method to set text color
    fun setTextColor(color: Int) {
        textPaint.color = color
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw rectangle
        val rectLeft = 50f
        val rectTop = 50f
        val rectRight = width - 50f
        val rectBottom = height - 50f
        canvas.drawRect(rectLeft, rectTop, rectRight, rectBottom, rectanglePaint)

        // Create StaticLayout for advanced text rendering
        val textWidth = width - 100 // Subtract padding
        val staticLayout = StaticLayout.Builder
            .obtain(text, 0, text.length, textPaint, textWidth)
            .setAlignment(textAlignment)
            .setLineSpacing(1f, 1f)
            .setIncludePad(true)
            .build()

        // Save canvas state
        canvas.save()

        // Translate canvas to center text vertically
        val textHeight = staticLayout.height
        val verticalOffset = (height - textHeight) / 2f
        canvas.translate(
            (width - textWidth) / 2f,
            verticalOffset
        )

        // Draw the text
        staticLayout.draw(canvas)

        // Restore canvas state
        canvas.restore()
    }
}


//call in side activity or fragment
<com.hardik.customveiw.views.CustomRectangleView
    android:layout_width="match_parent"
    android:layout_height="180dp"
    android:id="@+id/customRectangleView"/>
  
val customRectangleView = findViewById<CustomRectangleView>(R.id.customRectangleView)
customRectangleView.setText("Hardik with always KRISHNA!!!")
