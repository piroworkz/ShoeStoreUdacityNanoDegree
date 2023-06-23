package com.udacity.shoestore.ui.store.common

import android.os.Build
import android.widget.Button
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ui.store.master.views.CustomShoeTextView

@BindingAdapter("sendEvent")
fun TextInputEditText.sendEvent(sendEvent: (StoreEvent) -> Unit) {
    doOnTextChanged { text, _, _, _ ->
        when (id) {
            R.id.shoeName -> sendEvent(StoreEvent.OnNameChanged(text.toString()))
            R.id.shoeCompany -> sendEvent(StoreEvent.OnBrandChanged(text.toString()))
            R.id.shoeSize -> sendEvent(StoreEvent.OnSizeChanged(text.toString()))
            R.id.shoeDescription -> sendEvent(StoreEvent.OnDescriptionChanged(text.toString()))
        }
    }
}

@BindingAdapter("onButtonEvent")
fun Button.onButtonEvent(sendEvent: (StoreEvent) -> Unit) {
    setOnClickListener {
        when (id) {
            R.id.saveShoesButton -> sendEvent(StoreEvent.OnSave)
            R.id.cancelButton -> sendEvent(StoreEvent.OnCancel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.M)
@BindingAdapter("addShoe")
fun LinearLayout.addShoe(shoe: List<Shoe>?) {
    shoe?.forEach {
        val textView = CustomShoeTextView(context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).also { layoutParams: LinearLayout.LayoutParams ->
            layoutParams.setMargins(24, 16, 24, 16)
        }

        textView.run {
            layoutParams = params
            setTextColor(context.resources.getColor(R.color.colorWhite, null))
            textSize = 22f
            setText(it)
        }
        addView(textView)
    }
}