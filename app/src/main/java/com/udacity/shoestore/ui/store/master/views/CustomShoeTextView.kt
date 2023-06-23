package com.udacity.shoestore.ui.store.master.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.udacity.shoestore.domain.Shoe

class CustomShoeTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    fun setText(shoe: Shoe) {
        text = buildSpannedString {
            bold { append("Name: ") }
            appendLine(shoe.name)
            bold { append("Brand: ") }
            appendLine(shoe.company)
            bold { append("Size: ") }
            appendLine(shoe.size.toString())
            bold { append("Description: ") }
            appendLine(shoe.description)
        }
    }

}