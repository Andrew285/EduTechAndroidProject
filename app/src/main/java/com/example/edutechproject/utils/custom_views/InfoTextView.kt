package com.example.edutechproject.utils.custom_views

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
) : ViewGroup(context, attrs, defStyleAttr) {
    private val imageView: ImageView
    private val textView: TextView
    private val horizontalSpace: Int = 16 // 16 pixels

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.InfoTextView)
        val textSize = typedArray.getDimension(R.styleable.InfoTextView_textSize, 8f)
        val textFromView = typedArray.getString(R.styleable.InfoTextView_android_text)
            ?: resources.getString(R.string.customInfoText)
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
        // Measure the image
        measureChild(imageView, widthMeasureSpec, heightMeasureSpec)

        // Calculate the remaining width for the text
        val availableWidth = MeasureSpec.getSize(widthMeasureSpec) - imageView.measuredWidth - horizontalSpace - paddingLeft - paddingRight
        val textWidthSpec = MeasureSpec.makeMeasureSpec(availableWidth, MeasureSpec.AT_MOST)

        // Measure the text
        measureChild(textView, textWidthSpec, heightMeasureSpec)

        // Determine the total dimensions
        val width = paddingLeft + imageView.measuredWidth + horizontalSpace + textView.measuredWidth + paddingRight
        val height = paddingTop + maxOf(imageView.measuredHeight, textView.measuredHeight) + paddingBottom

        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        // Layout the image
        val imageLeft = paddingLeft
        val imageTop = paddingTop
        imageView.layout(
            imageLeft,
            imageTop,
            imageLeft + imageView.measuredWidth,
            imageTop + imageView.measuredHeight
        )

        // Layout the text
        val textLeft = imageLeft + imageView.measuredWidth + horizontalSpace
        val textTop = paddingTop
        textView.layout(
            textLeft,
            textTop,
            textLeft + textView.measuredWidth,
            textTop + textView.measuredHeight
        )
    }
}
