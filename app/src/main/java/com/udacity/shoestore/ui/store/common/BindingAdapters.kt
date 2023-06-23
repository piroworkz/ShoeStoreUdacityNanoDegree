package com.udacity.shoestore.ui.store.common

import android.os.Build
import android.widget.Button
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ui.store.master.views.CustomShoeTextView

@BindingAdapter("viewModel", "uiState")
fun Button.onButtonEvent(viewModel: StoreSharedViewModel?, uiState: StoreSharedViewModel.State) {
    setOnClickListener {
        when (id) {
            R.id.saveShoesButton -> viewModel?.sendEvent(StoreEvent.OnSave(uiState))
            R.id.cancelButton -> viewModel?.sendEvent(StoreEvent.OnCancel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.M)
@BindingAdapter("addShoe")
fun LinearLayout.addShoe(shoe: List<Shoe>?) {
    removeAllViews()
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