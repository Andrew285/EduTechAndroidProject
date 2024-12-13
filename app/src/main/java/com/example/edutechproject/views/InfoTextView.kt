package com.example.edutechproject.views

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.edutechproject.R

class InfoTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr)
{
    private val imageView: ImageView
    private val textView: TextView

    init {

        imageView = ImageView(context).apply {
            setImageResource(R.drawable.info_vector)
            layoutParams = LayoutParams(
                resources.getDimension(R.dimen.infoImageViewSize).toInt(),
                resources.getDimension(R.dimen.infoImageViewSize).toInt()
            )
        }

        textView = TextView(context).apply {
            text = resources.getString(R.string.customInfoText)
            textSize = resources.getDimension(R.dimen.textSizeNormal)
        }

        addView(imageView)
        addView(textView)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChild(imageView, widthMeasureSpec, heightMeasureSpec)
        measureChild(textView, widthMeasureSpec, heightMeasureSpec)

        val width = imageView.measuredWidth + textView.measuredWidth
        val height = maxOf(imageView.measuredHeight, textView.measuredHeight)

        setMeasuredDimension(width, height)
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        val imageLeft = paddingLeft
        val imageTop = paddingTop
        imageView.layout(
            imageLeft,
            imageTop,
            imageLeft + imageView.measuredWidth,
            imageTop + imageView.measuredHeight
        )

        val textLeft = imageLeft + imageView.measuredWidth + paddingRight
        val textTop = paddingTop
        textView.layout(
            textLeft,
            textTop,
            textLeft + textView.measuredWidth,
            textTop + textView.measuredHeight
        )
    }
}