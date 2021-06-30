package com.thedevexperto.test_android_challenge.Utils

import androidx.appcompat.app.AppCompatActivity
import com.thedevexperto.test_android_challenge.R

class MyToolbar {

    fun MostrarToolbar(MyActivity: AppCompatActivity, titulo:String?, BtnAtras:Boolean?){
        MyActivity.setSupportActionBar(MyActivity.findViewById(R.id.my_toolbar_custom))
        MyActivity.supportActionBar?.title = titulo
        MyActivity.supportActionBar?.setDisplayHomeAsUpEnabled(BtnAtras!!)

    }
}