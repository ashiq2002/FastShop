package com.setbitzero.fastshop.bindingAdapter

import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.setbitzero.fastshop.R
import com.setbitzero.fastshop.util.FormValidationUtil
import com.setbitzero.fastshop.util.InputTextType

@BindingAdapter("app:image")
fun image(view:ImageView, img:Int?){
    view.setImageResource(img!!)
}
