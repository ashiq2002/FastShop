package com.setbitzero.fastshop.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:image")
fun image(view:ImageView, img:Int?){
    view.setImageResource(img!!)
}
