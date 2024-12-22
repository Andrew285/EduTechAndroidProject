package com.example.edutechproject.view.custom

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
    private val horizontalSpace: Int = 16 // 16 pixels, for example

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.InfoTextView)
        val textSize = typedArray.getDimension(R.styleable.InfoTextView_textSize, 8f)
        val textFromView = typedArray.getString(R.styleable.InfoTextView_android_text) ?: resources.getString(R.string.customInfoText)
        typedArray.recycle()

        imageView = ImageView(context).apply {
            setImageResource(R.drawable.info_vector)
            layoutParams = LayoutParams(
                resources.getDimension(R.dimen.infoImageViewSize).toInt(),
                resources.getDimension(R.dimen.infoImageViewSize).toInt()
            )
        }

        textView = TextView(context).apply {
            this.text = textFromView
            this.textSize = textSize / 2
            maxLines = Int.MAX_VALUE // Allow unlimited lines
            ellipsize = null
        }

        addView(imageView)
        addView(textView)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChild(imageView, widthMeasureSpec, heightMeasureSpec)
        measureChild(textView, widthMeasureSpec, heightMeasureSpec)

        val width = imageView.measuredWidth + textView.measuredWidth + horizontalSpace
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

        val textLeft = imageLeft + imageView.measuredWidth + paddingRight + horizontalSpace
        val textTop = paddingTop
        textView.layout(
            textLeft,
            textTop,
            textLeft + textView.measuredWidth,
            textTop + textView.measuredHeight
        )
    }
}