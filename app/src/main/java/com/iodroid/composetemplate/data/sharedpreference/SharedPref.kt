package com.iodroid.composetemplate.data.sharedpreference

import android.content.Context
import com.iodroid.composetemplate.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPref @Inject constructor(@ApplicationContext context: Context) {
    private val prefs = context.getSharedPreferences(Constants.SHARED_PREF_NAME,Context.MODE_PRIVATE)

    fun getValue() : String{
        return prefs.getString("ddd","")!!
    }

    fun setValue(string : String){
        prefs.edit().putString("ddd", string).apply()
    }
}