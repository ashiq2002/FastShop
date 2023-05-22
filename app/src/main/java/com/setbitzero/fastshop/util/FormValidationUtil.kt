package com.setbitzero.fastshop.util

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

enum class InputTextType{EMAIL, PASSWORD}

object FormValidationUtil {

    //set error on TextInputLayout
    fun validate(textInputLayout: TextInputLayout, textInputEditText: TextInputEditText, inputTextType: InputTextType){
        textInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = s.toString()
                when(inputTextType){
                    InputTextType.EMAIL->{
                        textInputLayout.error = isEmailValid(text)
                    }
                    InputTextType.PASSWORD->{
                        textInputLayout.error = isPasswordValid(text)
                    }
                }
                Log.wtf("TEXT", text)
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }

    //validate email address
    private fun isEmailValid(email: String):String?{
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return "Invalid email address"
        }
        return null
    }

    //validate password
    private fun isPasswordValid(password: String):String?{
        if(password.length < 8){
            return "Minimum 8 Character Password"
        }
        if(!password.matches(".*[A-Z].*".toRegex())){
            return "Must contain 1 upper-case character"
        }
        if(!password.matches(".*[a-z].*".toRegex())){
            return "Must contain 1 lower-case character"
        }
        if(!password.matches(".*[0-9].*".toRegex())){
            return "Must contain 1 digit character"
        }

        if(!password.matches(".*[@#\$%^&*+=].*".toRegex())){
            return "Must contain 1 special character"
        }

        return  null
    }

}