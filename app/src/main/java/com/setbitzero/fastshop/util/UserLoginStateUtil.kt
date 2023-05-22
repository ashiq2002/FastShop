package com.setbitzero.fastshop.util

import android.content.Context
import android.content.SharedPreferences

class UserLoginStateUtil(context: Context) {
    private val dbName = "UserAuthState"
    private val emailKey = "email"
    private val passwordKey = "password"
    private val loginKey = "isLogin"
    private var sharedPref:SharedPreferences = context.getSharedPreferences(dbName, Context.MODE_PRIVATE)

    //save user email and password into the local database
    fun saveUserEmailPassword(email:String = "", password: String = "", save:Boolean = false){
        if(save){
            sharedPref
                .edit()
                .putString(emailKey, email)
                .putString(passwordKey, password)
                .apply()
        }
    }

    //save user login state into the local database
    fun saveLoginState(isLogin: Boolean){
        sharedPref
            .edit()
            .putBoolean(loginKey, isLogin)
            .apply()
    }

    //retrieve user email from local database
    fun getEmail():String?{
        return sharedPref.getString(emailKey, null)
    }

    //retrieve user password from local database
    fun getPassword():String?{
        return sharedPref.getString(passwordKey, null)
    }

    //retrieve user login state from local database
    fun isLoggedIn(): Boolean{
        return sharedPref.getBoolean(loginKey, false)
    }
}